package software.ivancic.geo.data.cache

import software.ivancic.geo.data.cache.db.PlacesDao
import software.ivancic.geo.data.cache.db.entity.PlaceEntity
import software.ivancic.geo.domain.usecases.Place

class CacheRepository(
    private val placesDao: PlacesDao
) {
    suspend fun getLatestPlaces() = placesDao.getLatestPlaces()

    suspend fun insertPlace(place: Place) {
        placesDao.insertAndUpdateDb(
            PlaceEntity(
                name = place.name,
                latitude = place.latitude,
                longitude = place.longitude,
                timestamp = System.currentTimeMillis(),
            )
        )
    }
}
