package software.ivancic.geo.ui.components

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import org.koin.compose.viewmodel.koinViewModel
import software.ivancic.geo.domain.usecases.Place
import software.ivancic.geo.ui.GeoViewModel
import timber.log.Timber

@SuppressLint("MissingPermission")
@Composable
fun AutoCompleteTextView(
    label: String,
    geoViewModel: GeoViewModel = koinViewModel(),
    onPlaceSelected: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        geoViewModel.submitAction(
            GeoViewModel.Action.OnLocationPermissionResult(isGranted)
        )
    }

    val effect by geoViewModel.effects.collectAsState(null)
    when (effect) {
        GeoViewModel.Effect.RequestLocationPermission -> {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        GeoViewModel.Effect.OnLocationPermissionDenied -> {
            // do nothing
        }

        GeoViewModel.Effect.OnLocationPermissionAccepted -> {
            val activity = LocalActivity.current ?: return
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

            val cancellationTokenSource = remember {
                CancellationTokenSource()
            }
            DisposableEffect(Unit) {
                fusedLocationClient.getCurrentLocation(
                    CurrentLocationRequest.Builder()
                        .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
                        .setDurationMillis(10_000)
                        .setMaxUpdateAgeMillis(30 * 60 * 1000) // 30 minutes
                        .build(),
                    cancellationTokenSource.token,
                ).addOnSuccessListener { location ->
                    location ?: return@addOnSuccessListener

                    geoViewModel.submitAction(
                        GeoViewModel.Action.GetCityAndWeatherDataFromLocation(
                            location.latitude,
                            location.longitude
                        )
                    )
                    onPlaceSelected(location.latitude, location.longitude)
                }.addOnFailureListener { error ->
                    Timber.e(error, "Issue retrieving location")
                }

                onDispose {
                    cancellationTokenSource.cancel()
                }
            }
        }

        null -> { /* no-op */
        }
    }

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

    LaunchedEffect(Unit) {
        geoViewModel.submitAction(
            GeoViewModel.Action.OnViewCreated
        )
    }
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
