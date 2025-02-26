package software.ivancic.geo.ui

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val geoUiDi = module {
    viewModel {
        GeoViewModel(get(), get(), get())
    }
}
