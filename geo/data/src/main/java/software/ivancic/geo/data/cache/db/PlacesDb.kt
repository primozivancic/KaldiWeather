package software.ivancic.geo.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import software.ivancic.geo.data.cache.db.entity.PlaceEntity

@Database(
    entities = [PlaceEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PlacesDb : RoomDatabase() {
    abstract fun getPlacesDao(): PlacesDao
}
