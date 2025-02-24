package software.ivancic.currentweather.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("time") var time: String? = null,
    @SerialName("temperature_2m_max") var temperature2mMax: String? = null,
    @SerialName("temperature_2m_min") var temperature2mMin: String? = null,
)
