package software.ivancic.geo.domain.usecases

import software.ivancic.core.domain.CoroutineDispatchers
import software.ivancic.core.domain.UseCase
import software.ivancic.geo.domain.GeoRepository

class SearchForMatchingPlacesUseCase(
    private val geoRepository: GeoRepository,
    coroutineDispatchers: CoroutineDispatchers,
) : UseCase<Query, List<Place>>(coroutineDispatchers.io) {
    override suspend fun execute(parameters: Query): List<Place> {
        return geoRepository.getPlacesForQuery(parameters.query)
    }
}

data class Query(
    val query: String,
)

data class Place(
    val name: String,
    val latitude: Double,
    val longitude: Double,
)
