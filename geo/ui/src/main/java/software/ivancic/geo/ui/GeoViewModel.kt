package software.ivancic.geo.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import software.ivancic.core.ui.BaseViewModel
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.domain.usecases.Query
import software.ivancic.geo.domain.usecases.SearchForMatchingPlacesUseCase
import software.ivancic.geo.ui.GeoViewModel.Action
import software.ivancic.geo.ui.GeoViewModel.Action.SearchForMatchingPlaces
import software.ivancic.geo.ui.GeoViewModel.Action.UpdateSearchQuery
import software.ivancic.geo.ui.GeoViewModel.Effect
import software.ivancic.geo.ui.GeoViewModel.State

class GeoViewModel(
    private val searchForMatchingPlaces: SearchForMatchingPlacesUseCase,
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
                if (action.query.text.length > 2) {
                    executeSearchAfterDelayJob = viewModelScope.launch {
                        delay(300)
                        executeSearch(action.query.text)
                    }
                }
            }

            is SearchForMatchingPlaces -> {
                executeSearchAfterDelayJob?.cancel()
                executeSearch(action.query.text)
            }

            Action.ClearSearchQuery -> {
                executeSearchAfterDelayJob?.cancel()
                updateState {
                    it.copy(
                        places = emptyList(),
                        searchQuery = it.searchQuery.copy(text = "")
                    )
                }
            }

            is Action.OnPredictionSelected -> {
                executeSearchAfterDelayJob?.cancel()
                updateState {
                    it.copy(
                        places = emptyList(),
                        searchQuery = it.searchQuery.copy(
                            text = action.place.name,
                            selection = TextRange(action.place.name.length)
                        ),
                    )
                }
            }
        }
    }

    private suspend fun executeSearch(query: String) {
        searchForMatchingPlaces(Query(query))
            .onSuccess { data ->
                updateState { it.copy(places = data) }
            }.onFailure {
                // todo show error
            }
    }

    sealed interface Action {
        data object ClearSearchQuery : Action
        data class SearchForMatchingPlaces(val query: TextFieldValue) : Action
        data class UpdateSearchQuery(val query: TextFieldValue) : Action
        data class OnPredictionSelected(val place: Place) : Action
    }

    sealed interface Effect
    data class State(
        val searchQuery: TextFieldValue = TextFieldValue(""),
        val places: List<Place> = emptyList(),
    )
}
