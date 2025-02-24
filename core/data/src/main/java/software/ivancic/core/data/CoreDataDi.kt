package software.ivancic.core.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

@Module
@ComponentScan("software.ivancic.core.data")
class CoreDataDI

val coreDataDi = module {
    single<Retrofit>(named(NamedInstancesNames.WeatherApi)) {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // let's say this is the default endpoint, so we can include it
        // in a core module
        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_API_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}

enum class NamedInstancesNames(val instanceQualifierName: String) {
    WeatherApi("weatherApi"),
}
