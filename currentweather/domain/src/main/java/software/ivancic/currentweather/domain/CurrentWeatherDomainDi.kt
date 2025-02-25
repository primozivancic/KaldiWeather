package software.ivancic.currentweather.domain

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import software.ivancic.currentweather.domain.usecases.GetCurrentWeatherDataForLocationUseCase

@Module
@ComponentScan("software.ivancic.currentweather.domain")
class CurrentWeatherDomainDi

val currentWeatherDomainDi = module {
    factory {
        GetCurrentWeatherDataForLocationUseCase(get(), get())
    }
}
