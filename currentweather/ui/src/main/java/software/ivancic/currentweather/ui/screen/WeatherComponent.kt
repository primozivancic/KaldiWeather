package software.ivancic.currentweather.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import software.ivancic.core.ui.theme.KaldiWeatherTheme
import software.ivancic.currentweather.ui.screen.weathercodes.DayOrNight
import software.ivancic.currentweather.ui.screen.weathercodes.Details

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
    weatherDetails: Details?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        weatherDetails?.let {
            AsyncImage(
                model = weatherDetails.icon,
                contentDescription = weatherDetails.description,
                modifier = Modifier
                    .size(120.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = true,
        ) {
            if (currentTemp != null && tempUnit != null) {
                item {
                    SingleDataElement(
                        title = "Current temperature",
                        data = currentTemp.toString() + tempUnit,
                    )
                }
            }
            if (feelsLikeTemp != null && tempUnit != null) {
                item {
                    SingleDataElement(
                        title = "Feels like",
                        data = feelsLikeTemp.toString() + tempUnit,
                    )
                }
            }
            if (minTemp != null && tempUnit != null) {
                item {
                    SingleDataElement(
                        title = "Min temperature",
                        data = minTemp.toString() + tempUnit,
                    )
                }
            }
            if (maxTemp != null && tempUnit != null) {
                item {
                    SingleDataElement(
                        title = "Max temperature",
                        data = maxTemp.toString() + tempUnit,
                    )
                }
            }
            if (humidity != null && humidityUnit != null) {
                item {
                    SingleDataElement(
                        title = "Humidity",
                        data = humidity.toString() + humidityUnit,
                    )
                }
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
            weatherDetails = Details(
                type = DayOrNight.DAY,
                description = "Sunny",
                icon = "http://openweathermap.org/img/wn/01d@2x.png",
            ),
        )
    }
}
