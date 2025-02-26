package software.ivancic.geo.domain.usecases

import software.ivancic.core.domain.CoroutineDispatchers
import software.ivancic.core.domain.UseCase
import software.ivancic.geo.domain.GeoRepository

class GetLatestPlacesUseCase(
    private val geoRepository: GeoRepository,
    coroutineDispatchers: CoroutineDispatchers,
) : UseCase<Unit, List<Place>>(coroutineDispatchers.io) {
    override suspend fun execute(parameters: Unit): List<Place> {
        return geoRepository.getLatestPlaces()
    }
}
