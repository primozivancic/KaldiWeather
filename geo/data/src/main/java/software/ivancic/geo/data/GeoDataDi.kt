package software.ivancic.geo.data

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import software.ivancic.geo.data.api.GeoService
import software.ivancic.geo.data.cache.CacheRepository
import software.ivancic.geo.data.cache.db.PlacesDao
import software.ivancic.geo.data.cache.db.PlacesDb
import software.ivancic.geo.domain.GeoRepository

val geoDataDi = module {
    single<Retrofit>(named(NamedInstancesNames.GeoApi)) {
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
            .baseUrl(BuildConfig.GEO_API_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    factory<GeoService> {
        get<Retrofit>(named(NamedInstancesNames.GeoApi))
            .create(GeoService::class.java)

    }

    factory<GeoRepository> {
        GeoRepositoryImpl(get(), get())
    }

    single<PlacesDb> {
        Room.databaseBuilder(
            androidContext(),
            PlacesDb::class.java,
            "places_database"
        ).build()
    }

    single<PlacesDao> {
        get<PlacesDb>().getPlacesDao()
    }

    single<CacheRepository> {
        CacheRepository(get())
    }
}

enum class NamedInstancesNames(private val instanceQualifierName: String) {
    GeoApi("geoApi"),
    ;

    override fun toString(): String {
        return instanceQualifierName
    }
}
