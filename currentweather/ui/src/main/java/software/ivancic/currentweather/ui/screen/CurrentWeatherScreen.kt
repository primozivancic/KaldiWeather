package software.ivancic.currentweather.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import software.ivancic.core.ui.theme.KaldiWeatherTheme
import software.ivancic.currentweather.ui.CurrentWeatherViewModel
import software.ivancic.currentweather.ui.Place

@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    CurrentWeatherScreenInternal(
        state = state,
        getWeatherForPlace = { place ->
            viewModel.submitAction(CurrentWeatherViewModel.Action.GetWeatherData(place))
        },
        modifier = modifier,
    )
}

@Composable
fun CurrentWeatherScreenInternal(
    state: CurrentWeatherViewModel.State,
    getWeatherForPlace: (Place) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        var query by remember { mutableStateOf("") }
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("City name") }
        )

        WeatherComponent(
            currentTemp = state.currentTemp,
            feelsLikeTemp = state.feelsLikeTemp,
            minTemp = state.minTemp,
            maxTemp = state.maxTemp,
            humidity = state.humidity,
            tempUnit = state.tempUnit,
            humidityUnit = state.humidityUnit,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    // todo test data - remove!
    LaunchedEffect(null) {
        getWeatherForPlace(
            Place(
                lat = 46.0511,
                lng = 14.5051,
                name = "some place",
            )
        )
    }
}

@Preview
@Composable
private fun CurrentWeatherScreenInternalPreview() {
    KaldiWeatherTheme {
        CurrentWeatherScreenInternal(
            state = CurrentWeatherViewModel.State(
                isLoading = false,
                currentTemp = 10.0,
                feelsLikeTemp = 10.0,
                minTemp = 10.0,
                maxTemp = 10.0,
                humidity = 10,
                tempUnit = "°C",
                humidityUnit = "%",
            ),
            getWeatherForPlace = {},
            modifier = Modifier
        )
    }
}
