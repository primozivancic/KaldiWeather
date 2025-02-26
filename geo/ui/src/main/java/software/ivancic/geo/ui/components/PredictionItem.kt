package software.ivancic.geo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun <T> PredictionItem(
    prediction: T,
    onItemClick: (T) -> Unit,
    predictionLabel: (T) -> String,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    Card(
        modifier
            .fillMaxWidth()
            .clickable {
                focusManager.clearFocus()
                onItemClick(prediction)
            }
            .padding(1.dp)) {
        Text(
            text = predictionLabel(prediction),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}
