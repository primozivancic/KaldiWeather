package software.ivancic.currentweather.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import software.ivancic.currentweather.data.api.model.Current
import software.ivancic.currentweather.data.api.model.CurrentUnits
import software.ivancic.currentweather.data.api.model.Daily
import software.ivancic.currentweather.data.api.model.DailyUnits

@Serializable
data class WeatherResponse(
    @SerialName("latitude") var latitude: Double,
    @SerialName("longitude") var longitude: Double,
    @SerialName("utc_offset_seconds") var utcOffsetSeconds: Int,
    @SerialName("timezone") var timezone: String,
    @SerialName("timezone_abbreviation") var timezoneAbbreviation: String,
    @SerialName("current_units") var currentUnits: CurrentUnits,
    @SerialName("current") var current: Current,
    @SerialName("daily_units") var dailyUnits: DailyUnits,
    @SerialName("daily") var daily: Daily,
)
