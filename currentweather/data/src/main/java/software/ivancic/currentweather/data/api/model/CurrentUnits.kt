package software.ivancic.currentweather.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnits(
    @SerialName("time") var time: String,
    @SerialName("interval") var interval: String,
    @SerialName("temperature_2m") var temperature2m: String,
    @SerialName("relative_humidity_2m") var relativeHumidity2m: String,
    @SerialName("apparent_temperature") var apparentTemperature: String,
    @SerialName("weather_code") var weatherCode: String,
)
