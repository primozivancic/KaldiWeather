package software.ivancic.geo.domain.usecases

import software.ivancic.core.domain.CoroutineDispatchers
import software.ivancic.core.domain.UseCase
import software.ivancic.geo.domain.GeoRepository

class SavePlaceToHistoryUseCase(
    private val geoRepository: GeoRepository,
    coroutineDispatchers: CoroutineDispatchers,
) : UseCase<Place, Unit>(coroutineDispatchers.io) {
    override suspend fun execute(parameters: Place) {
        geoRepository.savePlaceToHistory(parameters)
    }
}
