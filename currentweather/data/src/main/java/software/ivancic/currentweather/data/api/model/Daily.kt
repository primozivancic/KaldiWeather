package software.ivancic.currentweather.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    @SerialName("time") var time: ArrayList<String> = arrayListOf(),
    @SerialName("temperature_2m_max") var temperature2mMax: ArrayList<Double> = arrayListOf(),
    @SerialName("temperature_2m_min") var temperature2mMin: ArrayList<Double> = arrayListOf(),
)
