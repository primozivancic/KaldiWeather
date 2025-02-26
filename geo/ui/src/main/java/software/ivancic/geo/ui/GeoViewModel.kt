package software.ivancic.geo.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import software.ivancic.core.ui.BaseViewModel
import software.ivancic.geo.domain.usecases.FindPlaceNameFromLocation
import software.ivancic.geo.domain.usecases.GetLatestPlacesUseCase
import software.ivancic.geo.domain.usecases.Location
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.domain.usecases.Query
import software.ivancic.geo.domain.usecases.SavePlaceToHistoryUseCase
import software.ivancic.geo.domain.usecases.SearchForMatchingPlacesUseCase
import software.ivancic.geo.ui.GeoViewModel.Action
import software.ivancic.geo.ui.GeoViewModel.Action.*
import software.ivancic.geo.ui.GeoViewModel.Effect
import software.ivancic.geo.ui.GeoViewModel.State

class GeoViewModel(
    private val searchForMatchingPlaces: SearchForMatchingPlacesUseCase,
    private val savePlaceToHistory: SavePlaceToHistoryUseCase,
    private val getLatestPlaces: GetLatestPlacesUseCase,
    private val findPlaceNameFromLocation: FindPlaceNameFromLocation,
) : BaseViewModel<Action, Effect, State>(State()) {

    private var executeSearchAfterDelayJob: Job? = null

    override suspend fun handleAction(action: Action) {
        when (action) {
            is UpdateSearchQuery -> {
                if (action.query.text == state.value.searchQuery.text) {
                    return
                }

                executeSearchAfterDelayJob?.cancel()
                updateState { it.copy(searchQuery = action.query) }
                executeSearchAfterDelayJob = viewModelScope.launch {
                    delay(300)
                    executeSearch(action.query.text)
                }
            }

            is SearchForMatchingPlaces -> {
                executeSearchAfterDelayJob?.cancel()
                executeSearch(action.query.text)
            }

            is OnPredictionSelected -> {
                executeSearchAfterDelayJob?.cancel()
                savePlaceToHistory(action.place)
                updateState {
                    it.copy(
                        places = emptyList(),
                        searchQuery = it.searchQuery.copy(
                            text = action.place.name,
                            selection = TextRange(action.place.name.length)
                        ),
                        showPredictions = false
                    )
                }
            }

            OnSearchFieldFocusReceived -> {
                executeSearchAfterDelayJob?.cancel()
                if (state.value.searchQuery.text.isEmpty()) {
                    val items = getLatestPlaces(Unit).getOrDefault(emptyList())
                    updateState { it.copy(places = items) }
                }

                updateState { it.copy(showPredictions = true) }
            }

            OnClearClick -> {
                executeSearchAfterDelayJob?.cancel()
                updateState {
                    it.copy(
                        searchQuery = TextFieldValue(""),
                        showPredictions = false
                    )
                }
            }

            is OnLocationPermissionResult -> {
                if (action.granted) {
                    emitEffect(Effect.OnLocationPermissionAccepted)
                } else {
                    emitEffect(Effect.OnLocationPermissionDenied)
                }
            }

            OnViewCreated -> {
                emitEffect(Effect.RequestLocationPermission)
            }

            is GetCityAndWeatherDataFromLocation -> {
                findPlaceNameFromLocation(Location(action.latitude, action.longitude))
                    .onSuccess { city ->
                        updateState { it.copy(searchQuery = TextFieldValue(city, TextRange(city.length))) }
                    }
                    .onFailure {
                        // do nothing, fail silently
                    }
            }
        }
    }

    private suspend fun executeSearch(query: String) {
        searchForMatchingPlaces(Query(query))
            .onSuccess { data ->
                updateState { it.copy(places = data, showPredictions = true) }
            }.onFailure {
                // todo show error
            }
    }

    sealed interface Action {
        data object OnViewCreated : Action
        data object OnSearchFieldFocusReceived : Action
        data object OnClearClick : Action
        data class SearchForMatchingPlaces(val query: TextFieldValue) : Action
        data class UpdateSearchQuery(val query: TextFieldValue) : Action
        data class OnPredictionSelected(val place: Place) : Action
        data class OnLocationPermissionResult(val granted: Boolean) : Action
        data class GetCityAndWeatherDataFromLocation(val latitude: Double, val longitude: Double) :
            Action
    }

    sealed interface Effect {
        data object RequestLocationPermission : Effect
        data object OnLocationPermissionDenied : Effect
        data object OnLocationPermissionAccepted : Effect
    }

    data class State(
        val searchQuery: TextFieldValue = TextFieldValue(""),
        val showPredictions: Boolean = false,
        val places: List<Place> = emptyList(),
    )
}
