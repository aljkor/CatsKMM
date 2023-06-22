package si.razum.catskmm.android.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BreedCard(
    modifier: Modifier,
    name: String,
    temperament: String,
    imageUrl: String?,
    countryCode: String?,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp).heightIn(min = 100.dp)
            .clip(RoundedCornerShape(8.dp))
            //.background(Color.LightGray.copy(alpha = 0.2f))
            .background(Color.White).shadow(elevation = 4.dp, spotColor = Color.Transparent)
    ) {
        ImageBox(modifier = Modifier.heightIn(64.dp).align(CenterVertically).padding(4.dp),
            imageUrl = imageUrl,
            countryCode = countryCode)
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Text(
                name,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                temperament,
                style = TextStyle(fontSize = 12.sp, color = Color.DarkGray),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}