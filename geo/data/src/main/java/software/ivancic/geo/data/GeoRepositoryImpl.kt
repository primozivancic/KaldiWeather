package software.ivancic.geo.data

import software.ivancic.geo.data.api.GeoReverseService
import software.ivancic.geo.data.api.GeoService
import software.ivancic.geo.data.cache.CacheRepository
import software.ivancic.geo.domain.GeoRepository
import software.ivancic.geo.domain.usecases.Place

class GeoRepositoryImpl(
    private val geoService: GeoService,
    private val geoReverseService: GeoReverseService,
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

    override suspend fun getPlaceNameFromLocation(latitude: Double, longitude: Double): String {
        return with(geoReverseService.getLocationFromLatLng(latitude, longitude).address) {
            city ?: town ?: village ?: road ?: "Unknown"
        }
    }
}
