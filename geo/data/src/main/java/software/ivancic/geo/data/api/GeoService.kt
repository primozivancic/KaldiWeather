package software.ivancic.geo.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import software.ivancic.geo.data.api.model.GeoResponse

interface GeoService {
    @GET("/v1/search")
    suspend fun getPlacesForQuery(
        @Query("name") query: String,
    ): GeoResponse
}
