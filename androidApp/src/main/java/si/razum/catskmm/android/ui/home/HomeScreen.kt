package si.razum.catskmm.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import si.razum.catskmm.android.ui.home.components.BreedCard
import si.razum.catskmm.android.ui.home.components.SearchBar
import si.razum.catskmm.android.utils.Constants.Companion.BREED_DETAILS_BASE

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = getViewModel(),
    navController: NavController
) {
    val screenState by viewModel.stateFlow.collectAsState()
    val searchString = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(0.2f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (screenState) {
            is HomeScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.2f))
                }
            }

            is HomeScreenState.Content -> {
                SearchBar(searchString, viewModel::onSearchInput)
                LazyColumn {
                    items((screenState as HomeScreenState.Content).breedsList) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(100.dp)
                        ) {
                            BreedCard(
                                modifier = Modifier.clickable {
                                    navController.navigate("$BREED_DETAILS_BASE/${it.id}")
                                },
                                name = it.name,
                                temperament = it.temperament ?: "",
                                imageUrl = it.image?.url,
                                countryCode = it.countryCode
                            )
                        }

                    }
                }
            }
        }
    }
}