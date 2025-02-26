package software.ivancic.kaldiweather

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import software.ivancic.core.data.BuildConfig
import software.ivancic.core.data.coreDataDi
import software.ivancic.core.domain.coreDomainDi
import software.ivancic.currentweather.data.currentWeatherDataDi
import software.ivancic.currentweather.domain.currentWeatherDomainDi
import software.ivancic.currentweather.ui.currentWeatherUiDi
import software.ivancic.geo.data.geoDataDi
import software.ivancic.geo.domain.geoDomainDi
import software.ivancic.geo.ui.geoUiDi
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // todo plant something that reports exceptions or whatever
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                // core
                coreDomainDi,
                coreDataDi,

                // currentweather
                currentWeatherDataDi,
                currentWeatherDomainDi,
                currentWeatherUiDi,

                // geo
                geoDataDi,
                geoDomainDi,
                geoUiDi,
            )
        }
    }
}
