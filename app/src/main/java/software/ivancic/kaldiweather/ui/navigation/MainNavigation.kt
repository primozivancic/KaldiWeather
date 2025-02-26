package software.ivancic.kaldiweather.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import software.ivancic.currentweather.ui.screen.CurrentWeatherScreen

@Composable
fun MainNavigationScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.CurrentWeather,
        modifier = modifier,
    ) {
        composable<Destinations.CurrentWeather> {
            CurrentWeatherScreen(
                modifier = modifier
                    .fillMaxSize()
            )
        }
    }
}
