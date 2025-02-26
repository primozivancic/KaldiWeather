package software.ivancic.currentweather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import software.ivancic.currentweather.data.api.model.WeatherResponse

interface WeatherService {
    @GET("/v1/forecast?current=is_day,temperature_2m,relative_humidity_2m,apparent_temperature,weather_code&daily=temperature_2m_max,temperature_2m_min")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherResponse
}
