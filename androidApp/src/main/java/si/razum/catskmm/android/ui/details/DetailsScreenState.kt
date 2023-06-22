package si.razum.catskmm.android.ui.details

import si.razum.catskmm.remote.data.ImageListItem

sealed class DetailsScreenState {
    data class Content(val imageList: List<ImageListItem>): DetailsScreenState()
    object Loading: DetailsScreenState()
}