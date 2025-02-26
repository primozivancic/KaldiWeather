package software.ivancic.geo.domain

import software.ivancic.geo.domain.usecases.Place

interface GeoRepository {
    suspend fun getPlacesForQuery(query: String): List<Place>
}
