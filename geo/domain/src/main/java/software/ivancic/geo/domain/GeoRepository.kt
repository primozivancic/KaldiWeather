package software.ivancic.geo.domain

import software.ivancic.geo.domain.usecases.Place

interface GeoRepository {
    suspend fun getPlacesForQuery(query: String): List<Place>
    suspend fun savePlaceToHistory(place: Place)
    suspend fun getLatestPlaces(): List<Place>
    suspend fun getPlaceNameFromLocation(latitude: Double, longitude: Double): String
}
