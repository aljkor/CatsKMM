package si.razum.catskmm.android.ui.home

import si.razum.catskmm.remote.data.BreedsListItem

sealed class HomeScreenState {
    data class Content(val breedsList: List<BreedsListItem>, val filterValue: String): HomeScreenState()
    object Loading: HomeScreenState()
}