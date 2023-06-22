package si.razum.catskmm.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import si.razum.catskmm.remote.data.BreedsListItem
import si.razum.catskmm.repository.CatsRepositoryInterface

class HomeScreenViewModel(
    repository: CatsRepositoryInterface
): ViewModel() {
    /*val dataFlow: Flow<List<BreedsListItem>> = flow {
        emit(repository.getAllBreeds())
    }*/
    val dataFlow = repository.getAllBreedsAsFlow()

    val searchFlow: MutableStateFlow<String> = MutableStateFlow("")

    val stateFlow: StateFlow<HomeScreenState> =
        combine(dataFlow, searchFlow) {data, search -> toUiState(data, search)}
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = HomeScreenState.Loading)

    private fun toUiState(data: List<BreedsListItem>, search: String): HomeScreenState {
        val filtered = data.filter { item ->
            item.name.lowercase().contains(search.lowercase())
        }
        return HomeScreenState.Content(filtered, search)
    }

    fun onSearchInput(value: String) {
        searchFlow.tryEmit(value)
    }
}