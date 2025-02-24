package software.ivancic.currentweather.data

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import software.ivancic.core.data.NamedInstancesNames
import software.ivancic.currentweather.data.api.WeatherService

@Module
@ComponentScan("software.ivancic.currentweather.data")
class CurrentWeatherDataDI

val currentWeatherDataDi = module {
    single<WeatherService> {
        get<Retrofit>(named(NamedInstancesNames.WeatherApi.instanceQualifierName))
            .create(WeatherService::class.java)
    }
}
