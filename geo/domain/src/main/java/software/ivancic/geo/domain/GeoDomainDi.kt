package software.ivancic.geo.domain

import org.koin.dsl.module
import software.ivancic.geo.domain.usecases.SearchForMatchingPlacesUseCase

val geoDomainDi = module {
    factory {
        SearchForMatchingPlacesUseCase(get(), get())
    }
}
