package si.razum.catskmm.remote.data

import kotlinx.serialization.Serializable

@Serializable
data class ImageListItem(
    val breeds: List<BreedsListItem>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)