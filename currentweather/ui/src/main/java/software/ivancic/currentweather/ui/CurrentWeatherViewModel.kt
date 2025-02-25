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

//@KoinViewModel
class CurrentWeatherViewModel(
    private val getCurrentWeatherDataForLocation: GetCurrentWeatherDataForLocationUseCase,
) : BaseViewModel<Action, Effect, State>(
    State(false)
) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is GetWeatherData -> {
                viewModelScope.launch {
                    updateState { it.copy(isLoading = true) }
                    getCurrentWeatherDataForLocation(
                        LocationData(action.place.lat, action.place.lng)
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
        data class GetWeatherData(val place: Place) : Action
    }

    sealed interface Effect
    data class State(
        val isLoading: Boolean,

        val currentTemp: Double? = null,
        val feelsLikeTemp: Double? = null,
        val minTemp: Double? = null,
        val maxTemp: Double? = null,
        val humidity: Int? = null,
        val tempUnit: String? = null,
        val humidityUnit: String? = null,
    )
}
