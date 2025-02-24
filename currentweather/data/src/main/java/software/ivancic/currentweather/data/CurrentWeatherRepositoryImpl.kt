package software.ivancic.currentweather.data

import org.koin.core.annotation.Factory
import software.ivancic.currentweather.data.api.ApiRepository
import software.ivancic.currentweather.domain.CurrentWeatherRepository
import software.ivancic.currentweather.domain.usecases.CurrentWeather
import timber.log.Timber

@Factory
class CurrentWeatherRepositoryImpl(
    private val api: ApiRepository,
) : CurrentWeatherRepository {
    override suspend fun getWeatherData(lat: Double, lng: Double): CurrentWeather? {
        return try {
            api.getWeatherData(lat, lng)
        } catch (t: Throwable) {
            Timber.e(t = t, message = "Error while retrieving weather data")
            null
        }
    }
}
