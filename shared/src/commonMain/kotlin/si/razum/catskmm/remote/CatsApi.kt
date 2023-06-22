package si.razum.catskmm.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import si.razum.catskmm.remote.data.BreedsListItem
import si.razum.catskmm.remote.data.ImageListItem
import com.kuuurt.paging.multiplatform.helpers.dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

fun <T> Flow<T>.wrap(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()
        onEach { block(it) }.launchIn(CoroutineScope(job + Dispatchers.Main))//dispatcher()))

        return object : Closeable {
            override fun close() {
                println("evo mene closeam commonflow")
                job.cancel()
            }
        }
    }
}

class CatsApi(
    private val client: HttpClient,
    var baseUrl: String = "https://api.thecatapi.com/v1"
): KoinComponent {
    suspend fun getAllBreeds() = client.get("$baseUrl/breeds").body<List<BreedsListItem>>()

    fun getAllBreedsAsFlow() = flow {
        val result = client.get("$baseUrl/breeds").body<List<BreedsListItem>>()
        emit(result.drop(10))
        delay(3000)
        emit(result.drop(3))
        delay(3000)
        emit(result)
    }.wrap()


    suspend fun getBreedDetails(breedId: String) = client.get{
        url("$baseUrl/images/search")
        parameter("limit", 5)
        parameter("breed_ids", breedId)
    }.body<List<ImageListItem>>()
}