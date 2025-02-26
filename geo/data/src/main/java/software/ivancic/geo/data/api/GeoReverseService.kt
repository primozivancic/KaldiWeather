package software.ivancic.geo.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import software.ivancic.geo.data.api.model.GeoReverseResponse

interface GeoReverseService {
    @GET("/reverse?format=json")
    suspend fun getLocationFromLatLng(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ) : GeoReverseResponse
}
