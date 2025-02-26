package software.ivancic.geo.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import software.ivancic.core.ui.BaseViewModel
import software.ivancic.geo.domain.usecases.GetLatestPlacesUseCase
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.domain.usecases.Query
import software.ivancic.geo.domain.usecases.SavePlaceToHistoryUseCase
import software.ivancic.geo.domain.usecases.SearchForMatchingPlacesUseCase
import software.ivancic.geo.ui.GeoViewModel.Action
import software.ivancic.geo.ui.GeoViewModel.Action.OnClearClick
import software.ivancic.geo.ui.GeoViewModel.Action.OnPredictionSelected
import software.ivancic.geo.ui.GeoViewModel.Action.OnSearchFieldFocusReceived
import software.ivancic.geo.ui.GeoViewModel.Action.SearchForMatchingPlaces
import software.ivancic.geo.ui.GeoViewModel.Action.UpdateSearchQuery
import software.ivancic.geo.ui.GeoViewModel.Effect
import software.ivancic.geo.ui.GeoViewModel.State

class GeoViewModel(
    private val searchForMatchingPlaces: SearchForMatchingPlacesUseCase,
    private val savePlaceToHistory: SavePlaceToHistoryUseCase,
    private val getLatestPlaces: GetLatestPlacesUseCase,
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
        data object OnSearchFieldFocusReceived : Action
        data object OnClearClick : Action
        data class SearchForMatchingPlaces(val query: TextFieldValue) : Action
        data class UpdateSearchQuery(val query: TextFieldValue) : Action
        data class OnPredictionSelected(val place: Place) : Action
    }

    sealed interface Effect
    data class State(
        val searchQuery: TextFieldValue = TextFieldValue(""),
        val showPredictions: Boolean = false,
        val places: List<Place> = emptyList(),
    )
}
