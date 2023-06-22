package si.razum.catskmm.android.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import si.razum.catskmm.android.R

@Composable
fun ImageBox(
    modifier: Modifier,
    imageUrl: String?,
    countryCode: String?
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl, contentDescription = "Breed's image",
            modifier = Modifier
                .heightIn(min = 64.dp, max = 80.dp)
                .aspectRatio(1f)
                .align(Alignment.Center)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.cat_not_found),
            error = painterResource(id = R.drawable.cat_not_found),
            fallback = painterResource(id = R.drawable.cat_not_found)
        )
        AsyncImage(
            model = "https://flagcdn.com/32x24/${countryCode?.lowercase()}.png",
            contentDescription = "Country of origin flag",
            modifier = Modifier
                .heightIn(min = 24.dp, max = 32.dp)
                .aspectRatio(1f)
                .align(Alignment.BottomEnd)
            //.clip(CircleShape)
            ,
            placeholder = painterResource(id = R.drawable.cat_not_found),
            error = painterResource(id = R.drawable.cat_not_found),
            fallback = painterResource(id = R.drawable.cat_not_found)
        )
    }
}