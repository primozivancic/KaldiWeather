package software.ivancic.geo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            geoViewModel.submitAction(GeoViewModel.Action.ClearSearchQuery)
        },
        modifier = modifier,
    )
}

@Composable
private fun AutoCompleteTextViewInternal(
    label: String,
    query: TextFieldValue,
    predictions: List<Place>,
    onQueryChanged: (TextFieldValue) -> Unit,
    onSearchActionClicked: () -> Unit,
    onPredictionSelected: (Place) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        val focusManager = LocalFocusManager.current
        var showPredictions by remember { mutableStateOf(false) }

        LaunchedEffect(predictions) {
            showPredictions = predictions.isNotEmpty()
        }

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
            modifier = Modifier
                .fillMaxWidth()
        )
        if (predictions.isNotEmpty() && showPredictions) {
            Predictions(
                predictions = predictions,
                onPredictionSelected = {
                    onPredictionSelected(it)
                    showPredictions = false
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
