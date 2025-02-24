package software.ivancic.kaldiweather

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.software_ivancic_core_data_CoreDataDI
import org.koin.ksp.generated.software_ivancic_core_domain_CoreDomainDi
import org.koin.ksp.generated.software_ivancic_currentweather_data_CurrentWeatherDataDI
import org.koin.ksp.generated.software_ivancic_currentweather_domain_CurrentWeatherDomainDi
import org.koin.ksp.generated.software_ivancic_currentweather_ui_CurrentWeatherUiDi
import software.ivancic.core.data.BuildConfig
import software.ivancic.core.data.coreDataDi
import software.ivancic.currentweather.data.currentWeatherDataDi
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
                software_ivancic_core_domain_CoreDomainDi,
                software_ivancic_core_data_CoreDataDI,
                coreDataDi,
                software_ivancic_currentweather_data_CurrentWeatherDataDI,
                currentWeatherDataDi,
                software_ivancic_currentweather_domain_CurrentWeatherDomainDi,
                software_ivancic_currentweather_ui_CurrentWeatherUiDi,
            )
        }
    }
}
