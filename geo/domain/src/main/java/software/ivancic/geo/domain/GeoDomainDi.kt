package software.ivancic.geo.domain

import org.koin.dsl.module
import software.ivancic.geo.domain.usecases.GetLatestPlacesUseCase
import software.ivancic.geo.domain.usecases.SavePlaceToHistoryUseCase
import software.ivancic.geo.domain.usecases.SearchForMatchingPlacesUseCase

val geoDomainDi = module {
    factory {
        SearchForMatchingPlacesUseCase(get(), get())
    }

    factory {
        SavePlaceToHistoryUseCase(get(), get())
    }

    factory {
        GetLatestPlacesUseCase(get(), get())
    }
}
