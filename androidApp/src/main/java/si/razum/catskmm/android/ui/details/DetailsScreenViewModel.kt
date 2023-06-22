package si.razum.catskmm.android.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import si.razum.catskmm.repository.CatsRepositoryInterface


class DetailsScreenViewModel(
    repository: CatsRepositoryInterface,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val dataFlow: StateFlow<DetailsScreenState> = flow {
        val results =
            savedStateHandle.get<String>("breedId")?.let { repository.getBreedDetails(it) }
        if (!results.isNullOrEmpty()) {
            emit(DetailsScreenState.Content(results))
        } else {
            emit(DetailsScreenState.Loading)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        initialValue = DetailsScreenState.Loading
    )
}