@file:OptIn(ExperimentalCoroutinesApi::class)

package software.ivancic.geo.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import software.ivancic.geo.data.api.GeoService
import software.ivancic.geo.data.cache.CacheRepository
import software.ivancic.geo.domain.GeoRepository
import software.ivancic.geo.domain.usecases.Place

class GeoRepositoryImpl(
    private val geoService: GeoService,
    private val cacheRepository: CacheRepository,
) : GeoRepository {
    override suspend fun getPlacesForQuery(query: String): List<Place> {
        return geoService.getPlacesForQuery(query).results
            .map {
                Place(
                    name = it.name,
                    latitude = it.latitude,
                    longitude = it.longitude,
                )
            }
    }

    override suspend fun savePlaceToHistory(place: Place) {
        cacheRepository.insertPlace(place)
    }

    override suspend fun getLatestPlaces() = cacheRepository.getLatestPlaces().map { entity ->
        Place(
            name = entity.name,
            latitude = entity.latitude,
            longitude = entity.longitude,
        )
    }
}
