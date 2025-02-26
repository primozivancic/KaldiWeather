package software.ivancic.currentweather.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import software.ivancic.core.ui.BaseViewModel
import software.ivancic.currentweather.domain.usecases.GetCurrentWeatherDataForLocationUseCase
import software.ivancic.currentweather.domain.usecases.LocationData
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.Action
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.Action.GetWeatherData
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.Effect
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.State
import software.ivancic.currentweather.ui.screen.weathercodes.Codes
import software.ivancic.currentweather.ui.screen.weathercodes.DayOrNight
import software.ivancic.currentweather.ui.screen.weathercodes.Details

//@KoinViewModel
class CurrentWeatherViewModel(
    private val getCurrentWeatherDataForLocation: GetCurrentWeatherDataForLocationUseCase,
) : BaseViewModel<Action, Effect, State>(State()) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is GetWeatherData -> {
                viewModelScope.launch {
                    updateState { it.copy(isLoading = true) }
                    getCurrentWeatherDataForLocation(
                        LocationData(action.latitude, action.longitude)
                    ).onSuccess { data ->
                        updateState {
                            it.copy(
                                isLoading = false,
                                currentTemp = data.currentTemp,
                                feelsLikeTemp = data.feelsLikeTemp,
                                minTemp = data.minTemp,
                                maxTemp = data.maxTemp,
                                humidity = data.humidity,
                                tempUnit = data.tempUnit,
                                humidityUnit = data.humidityUnit,
                                weatherDetails = Codes.getDetailsFor(
                                    code = data.weatherCode,
                                    type = if (data.isDay) DayOrNight.DAY else DayOrNight.NIGHT,
                                ),
                            )
                        }
                    }.onFailure {
                        // todo show error
                        updateState { it.copy(isLoading = false) }
                    }
                }
            }
        }
    }

    sealed interface Action {
        data class GetWeatherData(val latitude: Double, val longitude: Double) : Action
    }

    sealed interface Effect

    data class State(
        val isLoading: Boolean = false,

        val currentTemp: Double? = null,
        val feelsLikeTemp: Double? = null,
        val minTemp: Double? = null,
        val maxTemp: Double? = null,
        val humidity: Int? = null,
        val tempUnit: String? = null,
        val humidityUnit: String? = null,
        val weatherDetails: Details? = null,
    )
}
