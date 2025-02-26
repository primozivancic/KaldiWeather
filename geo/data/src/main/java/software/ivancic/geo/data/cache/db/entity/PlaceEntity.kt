package software.ivancic.geo.data.cache.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
)
