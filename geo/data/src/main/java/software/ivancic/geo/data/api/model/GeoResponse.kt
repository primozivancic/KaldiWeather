package software.ivancic.geo.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoResponse(
    @SerialName("results") var results: List<Result> = emptyList(),
)
