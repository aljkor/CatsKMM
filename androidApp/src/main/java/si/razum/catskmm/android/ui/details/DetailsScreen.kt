package si.razum.catskmm.android.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel
import si.razum.catskmm.android.R
import si.razum.catskmm.remote.data.ImageListItem

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = getViewModel(),
    navController: NavController
) {
    val dataState by viewModel.dataFlow.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    if (dataState is DetailsScreenState.Content) {
                        val results: List<ImageListItem> =
                            (dataState as DetailsScreenState.Content).imageList

                        Text(
                            text = results[0].breeds[0].name,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(text = "")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        }
    ) { padd ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(0.2f))
                .padding(padd)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (dataState) {
                is DetailsScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize(0.2f)
                                .align(Alignment.Center)
                        )
                    }
                }

                is DetailsScreenState.Content -> {
                    val results: List<ImageListItem> =
                        (dataState as DetailsScreenState.Content).imageList
                    if (results.isNotEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = results[0].breeds[0].description,
                                fontSize = 24.sp,
                                style = TextStyle(color = Color.DarkGray.copy(0.7f)),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(color = Color.LightGray, thickness = 1.dp)
                            Spacer(modifier = Modifier.height(4.dp))
                            results[0].breeds[0].temperament.let {
                                Text(
                                    text = "Temperament", textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        Color.DarkGray,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Text(
                                    text = it,
                                    fontSize = 24.sp,
                                    style = TextStyle(color = Color.DarkGray.copy(0.7f)),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Divider(color = Color.LightGray, thickness = 1.dp)
                            }
                            results[0].breeds[0].affectionLevel?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Affection", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].intelligence?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Intelligence", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].energyLevel?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Energy", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].bidability?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Bidability", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].childFriendly?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Child friendly", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].catFriendly?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Cat friendly", rating = it, bestRating = 5)
                            }
                            results[0].breeds[0].dogFriendly?.let {
                                Spacer(modifier = Modifier.height(4.dp))
                                RatingRows(type = "Dog friendly", rating = it, bestRating = 5)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyRow {
                                items(results) {
                                    AsyncImage(
                                        model = it.url, contentDescription = "Breed's image",
                                        modifier = Modifier
                                            .heightIn(min = 120.dp, max = 160.dp)
                                            .aspectRatio(1f)
                                            .clip(RoundedCornerShape(4.dp)),
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = R.drawable.cat_not_found),
                                        error = painterResource(id = R.drawable.cat_not_found),
                                        fallback = painterResource(id = R.drawable.cat_not_found)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun RatingRows(
    type: String,
    rating: Int,
    bestRating: Int
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = type, textAlign = TextAlign.Start,
            style = TextStyle(
                Color.Black,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .weight(0.5f)
                .padding(8.dp)
        )
        Row(
            modifier = Modifier
                .weight(0.5f)
                .padding(8.dp)
        ) {
            repeat(rating) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
            repeat(bestRating - rating) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
        }

    }
}