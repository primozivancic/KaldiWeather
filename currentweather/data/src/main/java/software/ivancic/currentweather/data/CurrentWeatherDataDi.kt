package software.ivancic.currentweather.data

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import software.ivancic.core.data.NamedInstancesNames
import software.ivancic.currentweather.data.api.ApiRepository
import software.ivancic.currentweather.data.api.WeatherService
import software.ivancic.currentweather.domain.CurrentWeatherRepository

//@Module
//@ComponentScan
//class CurrentWeatherDataDi

val currentWeatherDataDi = module {
    factory<WeatherService> {
        get<Retrofit>(named(NamedInstancesNames.WeatherApi))
            .create(WeatherService::class.java)
    }

    factory {
        ApiRepository(get())
    }

    factory<CurrentWeatherRepository> {
        CurrentWeatherRepositoryImpl(get())
    }
}
