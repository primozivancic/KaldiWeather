package software.ivancic.kaldiweather.ui.navigation

import kotlinx.serialization.Serializable

internal sealed interface Destinations {
    @Serializable
    data object CurrentWeather : Destinations
}
