package software.ivancic.currentweather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import software.ivancic.currentweather.data.api.model.WeatherResponse

// https://api.open-meteo.com/v1/forecast?latitude=46.0511&longitude=14.5051&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code&daily=temperature_2m_max,temperature_2m_min
interface WeatherService {
    @GET("/v1/forecast?current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code&daily=temperature_2m_max,temperature_2m_min")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherResponse
}
