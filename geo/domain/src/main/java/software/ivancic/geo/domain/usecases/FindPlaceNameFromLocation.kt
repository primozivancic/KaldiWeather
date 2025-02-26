package software.ivancic.geo.domain.usecases

import software.ivancic.core.domain.CoroutineDispatchers
import software.ivancic.core.domain.UseCase
import software.ivancic.geo.domain.GeoRepository

class FindPlaceNameFromLocation(
    private val geoRepository: GeoRepository,
    coroutineDispatchers: CoroutineDispatchers,
) : UseCase<Location, String>(
    coroutineDispatchers.io
){
    override suspend fun execute(parameters: Location): String {
        return geoRepository.getPlaceNameFromLocation(parameters.latitude, parameters.longitude)
    }
}

data class Location(
    val latitude: Double,
    val longitude: Double,
)
