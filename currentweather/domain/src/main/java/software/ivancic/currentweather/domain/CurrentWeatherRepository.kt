package software.ivancic.currentweather.domain

import software.ivancic.currentweather.domain.usecases.CurrentWeather

interface CurrentWeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): CurrentWeather?
}
