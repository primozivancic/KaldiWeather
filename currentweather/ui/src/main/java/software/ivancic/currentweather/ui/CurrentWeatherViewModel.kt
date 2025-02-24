package software.ivancic.currentweather.ui

import org.koin.android.annotation.KoinViewModel
import software.ivancic.core.ui.BaseViewModel
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.Action
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.Effect
import software.ivancic.currentweather.ui.CurrentWeatherViewModel.State

@KoinViewModel
class CurrentWeatherViewModel : BaseViewModel<Action, Effect, State>(
    State(false)
) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            else -> TODO()
        }
    }

    sealed interface Action
    sealed interface Effect
    data class State(
        val isLoading: Boolean,
    )
}
