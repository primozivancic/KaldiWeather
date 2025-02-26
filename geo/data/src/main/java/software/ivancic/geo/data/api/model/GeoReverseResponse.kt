package software.ivancic.geo.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoReverseResponse(
    @SerialName("address") val address: Address,
)

@Serializable
data class Address(
    @SerialName("city") val city: String? = null,
    @SerialName("town") val town: String? = null,
    @SerialName("village") val village: String? = null,
    @SerialName("road") val road: String? = null,
)
