package software.ivancic.currentweather.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import software.ivancic.core.ui.theme.KaldiWeatherTheme
import software.ivancic.currentweather.ui.CurrentWeatherViewModel
import software.ivancic.currentweather.ui.screen.components.WeatherComponent
import software.ivancic.geo.ui.components.AutoCompleteTextView
import software.ivancic.ui.R

@Composable
fun CurrentWeatherScreen(
    currentWeatherViewModel: CurrentWeatherViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val weatherState by currentWeatherViewModel.state.collectAsState()

    CurrentWeatherScreenInternal(
        weatherState = weatherState,
        onPredictionSelected = { lat, lng ->
            currentWeatherViewModel.submitAction(
                CurrentWeatherViewModel.Action.GetWeatherData(
                    lat,
                    lng
                )
            )
        },
        modifier = modifier,
    )
}

@Composable
fun CurrentWeatherScreenInternal(
    weatherState: CurrentWeatherViewModel.State,
    onPredictionSelected: (Double, Double) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
    ) {
        AutoCompleteTextView(
            label = stringResource(R.string.enter_location),
            onPlaceSelected = onPredictionSelected,
            modifier = Modifier
                .fillMaxWidth()
        )

        WeatherComponent(
            currentTemp = weatherState.currentTemp,
            feelsLikeTemp = weatherState.feelsLikeTemp,
            minTemp = weatherState.minTemp,
            maxTemp = weatherState.maxTemp,
            humidity = weatherState.humidity,
            tempUnit = weatherState.tempUnit,
            humidityUnit = weatherState.humidityUnit,
            weatherDetails = weatherState.weatherDetails,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun CurrentWeatherScreenInternalPreview() {
    KaldiWeatherTheme {
        CurrentWeatherScreenInternal(
            weatherState = CurrentWeatherViewModel.State(
                isLoading = false,
                currentTemp = 10.0,
                feelsLikeTemp = 10.0,
                minTemp = 10.0,
                maxTemp = 10.0,
                humidity = 10,
                tempUnit = "°C",
                humidityUnit = "%",
            ),
            onPredictionSelected = { _, _ -> },
            modifier = Modifier
        )
    }
}
