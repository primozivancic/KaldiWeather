package software.ivancic.currentweather.ui

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//@Module
//@ComponentScan("software.ivancic.currentweather.ui")
//class CurrentWeatherUiDi

val currentWeatherUiDi = module {
    viewModel {
        CurrentWeatherViewModel(get())
    }
}
