package software.ivancic.currentweather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import software.ivancic.core.ui.theme.KaldiWeatherTheme
import software.ivancic.ui.R

@Composable
fun WeatherComponent(
    modifier: Modifier = Modifier,
    currentTemp: Double?,
    feelsLikeTemp: Double?,
    minTemp: Double?,
    maxTemp: Double?,
    humidity: Int?,
    tempUnit: String?,
    humidityUnit: String?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground), // todo replace with the actual weather image
            contentDescription = stringResource(R.string.weather_representation_image),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                )
        ) { // todo add all the different data
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = currentTemp.toString() + tempUnit)
                Text(text = feelsLikeTemp.toString() + tempUnit)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = minTemp.toString() + tempUnit)
                Text(text = maxTemp.toString() + tempUnit)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = humidity.toString() + humidityUnit)
            }
        }
    }
}

@Preview
@Composable
private fun WeatherComponentPreview() {
    KaldiWeatherTheme {
        WeatherComponent(
            currentTemp = 10.0,
            feelsLikeTemp = 10.0,
            minTemp = 10.0,
            maxTemp = 10.0,
            humidity = 10,
            tempUnit = "°C",
            humidityUnit = "%",
        )
    }
}
