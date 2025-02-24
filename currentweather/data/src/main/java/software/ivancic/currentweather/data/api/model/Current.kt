package software.ivancic.currentweather.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("time") var time: String,
    @SerialName("interval") var interval: Int,
    @SerialName("temperature_2m") var temperature2m: Double,
    @SerialName("relative_humidity_2m") var relativeHumidity2m: Int,
    @SerialName("apparent_temperature") var apparentTemperature: Double,
    @SerialName("weather_code") var weatherCode: Int,
)
