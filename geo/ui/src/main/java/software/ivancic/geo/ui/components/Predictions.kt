package software.ivancic.geo.ui.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.ui.components.positionproviders.PredictionsPositionProvider

@Composable
fun Predictions(
    predictions: List<Place>,
    onPredictionSelected: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current
    val minHeight = TextFieldDefaults.MinHeight * 3
    var popupMaxHeight by remember { mutableStateOf(minHeight) }
    Popup(
        popupPositionProvider = PredictionsPositionProvider(
            minHeight = with(density) { minHeight.roundToPx() },
            onPositionCalculated = { maxHeight ->
                popupMaxHeight = with(density) { maxHeight.toDp() }
            }),
    ) {
        LazyColumn(
            modifier = modifier
                .heightIn(max = popupMaxHeight)
                .padding(horizontal = 16.dp),
            state = lazyListState,
        ) {
            items(predictions) { prediction ->
                PredictionItem(
                    prediction = prediction,
                    predictionLabel = {
                        it.name
                    },
                    onItemClick = onPredictionSelected,
                )
            }
        }
    }
}
