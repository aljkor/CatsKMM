package si.razum.catskmm.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import si.razum.catskmm.remote.CatsApi
import si.razum.catskmm.repository.CatsRepository
import si.razum.catskmm.repository.CatsRepositoryInterface
import si.razum.catskmm.repository.platformModule

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs), platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { createJson() }
    single { createHttpClient(get(), get()) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single<CatsRepositoryInterface> { CatsRepository() }

    single { CatsApi(get()) }

}

fun createJson() = Json {  ignoreUnknownKeys = true }


fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        defaultRequest {
            header("x-api-key", "live_4ltnwLYKh1WYfH8QBm7cXA21yAT41c2NW1cP3nS7QVJb4j7sXANpDFV0dDOdIrhv")
        }
    }
