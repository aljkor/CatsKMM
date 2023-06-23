package si.razum.catskmm.repository

//import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import si.razum.catskmm.remote.CatsApi
import si.razum.catskmm.remote.CommonFlow
import si.razum.catskmm.remote.data.BreedsListItem
import si.razum.catskmm.remote.data.ImageListItem
//import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.MainScope

interface CatsRepositoryInterface {

    suspend fun getAllBreeds(): List<BreedsListItem>

    suspend fun getBreedDetails(breedId: String): List<ImageListItem>

    //@NativeCoroutines
    fun getAllBreedsAsFlow(): Flow<List<BreedsListItem>>

    /*@NativeCoroutines
    val letsTryAgainFlow: Flow<List<BreedsListItem>>*/
}

class CatsRepository: KoinComponent, CatsRepositoryInterface {
    private val catsApi: CatsApi by inject()

    /*@NativeCoroutineScope
    val coroutineScope: CoroutineScope = MainScope()*/

    override suspend fun getAllBreeds(): List<BreedsListItem> {
        return catsApi.getAllBreeds()
    }

    override suspend fun getBreedDetails(breedId: String): List<ImageListItem> {
        return catsApi.getBreedDetails(breedId)
    }

    override fun getAllBreedsAsFlow(): CommonFlow<List<BreedsListItem>> {
        return catsApi.getAllBreedsAsFlow()
    }

    /*@NativeCoroutines
    override val letsTryAgainFlow: Flow<List<BreedsListItem>> = catsApi.letsTryAgainAsFlow()
    */
}