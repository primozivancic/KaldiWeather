package software.ivancic.currentweather.domain.usecases

import org.koin.core.annotation.Factory
import software.ivancic.core.domain.CoroutineDispatchers
import software.ivancic.core.domain.UseCase
import software.ivancic.currentweather.domain.CurrentWeatherRepository

@Factory
class GetCurrentWeatherDataForLocationUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository,
    coroutineDispatchers: CoroutineDispatchers,
) : UseCase<LocationData, CurrentWeather?>(
    coroutineDispatchers.io
) {
    override suspend fun execute(parameters: LocationData): CurrentWeather? {
        return currentWeatherRepository.getWeatherData(
            lat = parameters.latitude,
            lng = parameters.longitude,
        )
    }
}

data class LocationData(
    val latitude: Double,
    val longitude: Double,
)

data class CurrentWeather(
    val currentTemp: Double,
    val feelsLikeTemp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Int,
    val tempUnit: String,
    val humidityUnit: String,
)
