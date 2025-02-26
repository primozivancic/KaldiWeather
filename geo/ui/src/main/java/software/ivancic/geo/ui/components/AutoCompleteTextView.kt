package software.ivancic.geo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import org.koin.compose.viewmodel.koinViewModel
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.ui.GeoViewModel

@Composable
fun AutoCompleteTextView(
    label: String,
    geoViewModel: GeoViewModel = koinViewModel(),
    onPlaceSelected: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
) {

    val state by geoViewModel.state.collectAsState()

    AutoCompleteTextViewInternal(
        label = label,
        query = state.searchQuery,
        predictions = state.places,
        showPredictions = state.showPredictions,
        onQueryChanged = {
            geoViewModel.submitAction(GeoViewModel.Action.UpdateSearchQuery(it))
        },
        onSearchActionClicked = {
            geoViewModel.submitAction(GeoViewModel.Action.SearchForMatchingPlaces(state.searchQuery))
        },
        onPredictionSelected = { place ->
            geoViewModel.submitAction(GeoViewModel.Action.OnPredictionSelected(place))
            onPlaceSelected(place.latitude, place.longitude)
        },
        onClearClick = {
            geoViewModel.submitAction(GeoViewModel.Action.OnClearClick)
        },
        onFocusReceived = {
            geoViewModel.submitAction(GeoViewModel.Action.OnSearchFieldFocusReceived)
        },
        modifier = modifier,
    )
}

@Composable
private fun AutoCompleteTextViewInternal(
    label: String,
    query: TextFieldValue,
    predictions: List<Place>,
    showPredictions: Boolean,
    onQueryChanged: (TextFieldValue) -> Unit,
    onSearchActionClicked: () -> Unit,
    onPredictionSelected: (Place) -> Unit,
    onClearClick: () -> Unit,
    onFocusReceived: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        val focusManager = LocalFocusManager.current

        SearchField(
            label = label,
            query = query,
            onQueryChanged = onQueryChanged,
            onSearchActionClicked = {
                focusManager.clearFocus(true)
                onSearchActionClicked()
            },
            onClearClick = {
                onClearClick()
                focusManager.clearFocus(true)
            },
            onFocusReceived = onFocusReceived,
            modifier = Modifier
                .fillMaxWidth()
        )
        if (showPredictions) {
            Predictions(
                predictions = predictions,
                onPredictionSelected = {
                    onPredictionSelected(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
