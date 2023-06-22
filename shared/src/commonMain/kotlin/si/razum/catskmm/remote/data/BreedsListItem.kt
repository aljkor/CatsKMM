package si.razum.catskmm.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedsListItem(
    val adaptability: Int? = null,
    @SerialName("affection_level")
    val affectionLevel: Int? = null,
    @SerialName("alt_names")
    val altNames: String? = null,
    @SerialName("cfa_url")
    val cfaUrl: String? = null,
    @SerialName("child_friendly")
    val childFriendly: Int? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    @SerialName("country_codes")
    val countryCodes: String? = null,
    val description: String,
    @SerialName("dog_friendly")
    val dogFriendly: Int? = null,
    @SerialName("energy_level")
    val energyLevel: Int? = null,
    val experimental: Int? = null,
    val grooming: Int? = null,
    val hairless: Int? = null,
    @SerialName("health_issues")
    val healthIssues: Int? = null,
    val hypoallergenic: Int? = null,
    val id: String,
    val indoor: Int? = null,
    val intelligence: Int? = null,
    val lap: Int? = null,
    @SerialName("life_span")
    val lifeSpan: String? = null,
    val name: String,
    val natural: Int? = null,
    val origin: String? = null,
    val rare: Int? = null,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null,
    val rex: Int? = null,
    @SerialName("shedding_level")
    val sheddingLevel: Int? = null,
    @SerialName("short_legs")
    val shortLegs: Int? = null,
    @SerialName("social_needs")
    val socialNeeds: Int? = null,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int? = null,
    @SerialName("suppressed_tail")
    val suppressedTail: Int? = null,
    val temperament: String,
    @SerialName("vcahospitals_url")
    val vcahospitalsUrl: String? = null,
    @SerialName("vetstreet_url")
    val vetstreetUrl: String? = null,
    val vocalisation: Int? = null,
    val weight: Weight? = null,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = null,
    @SerialName("cat_friendly")
    val catFriendly: Int? = null,
    val bidability: Int? = null,
    val image: ImageItem? = null
)
