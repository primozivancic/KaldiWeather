package software.ivancic.geo.data

import software.ivancic.geo.data.api.GeoService
import software.ivancic.geo.domain.GeoRepository
import software.ivancic.geo.domain.usecases.Place

class GeoRepositoryImpl(
    private val geoService: GeoService,
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
}
