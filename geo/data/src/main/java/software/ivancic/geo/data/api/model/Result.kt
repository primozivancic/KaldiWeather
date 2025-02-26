package software.ivancic.geo.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("id") var id: Int,
    @SerialName("name") var name: String,
    @SerialName("latitude") var latitude: Double,
    @SerialName("longitude") var longitude: Double,
)
