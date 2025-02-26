package software.ivancic.geo.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import software.ivancic.geo.data.cache.db.entity.PlaceEntity

@Dao
interface PlacesDao {

    @Query("SELECT * FROM places ORDER BY timestamp DESC LIMIT 5")
    suspend fun getLatestPlaces(): List<PlaceEntity>

    @Transaction
    suspend fun insertAndUpdateDb(place: PlaceEntity) {
        insert(place)
        deleteOlder()
    }

    /**
     * This should only be used from inside `PlacesDao` class.
     * For adding new places, use `insertAndUpdateDb` to make sure that
     * the DB only contains last 5 entries
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: PlaceEntity)

    /**
     * This should only be called from inside `PlacesDao` class.
     */
    @Query(
        "DELETE FROM places " +
                "WHERE name NOT IN " +
                "(SELECT name FROM places " +
                "   ORDER BY timestamp DESC LIMIT 5)"
    )
    suspend fun deleteOlder()
}
