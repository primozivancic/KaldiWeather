package software.ivancic.currentweather.data.api

import org.koin.core.annotation.Factory
import software.ivancic.currentweather.domain.usecases.CurrentWeather

//@Factory
class ApiRepository(
    private val weatherService: WeatherService,
) {
    suspend fun getWeatherData(
        lat: Double,
        lng: Double,
    ): CurrentWeather {
        return weatherService.getCurrentWeather(lat, lng).let {
            CurrentWeather(
                currentTemp = it.current.temperature2m,
                feelsLikeTemp = it.current.apparentTemperature,
                minTemp = it.daily.temperature2mMin.first(),
                maxTemp = it.daily.temperature2mMax.first(),
                humidity = it.current.relativeHumidity2m,
                tempUnit = it.currentUnits.temperature2m,
                humidityUnit = it.currentUnits.relativeHumidity2m,
            )
        }
    }
}
