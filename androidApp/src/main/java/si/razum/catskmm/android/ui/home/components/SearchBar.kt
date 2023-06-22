package si.razum.catskmm.android.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchString: MutableState<String>,
    onSearchInput: (String) -> Unit,
    placeholder: String = "Search"
) {
    val searchFocused = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxWidth().height(48.dp).padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = searchString.value,
            onValueChange = { newText ->
                if (!newText.contains("\n")) {
                    searchString.value = newText
                    onSearchInput(newText)
                }
            },
            modifier = Modifier.fillMaxSize()
                .border(2.dp, Color.LightGray, RoundedCornerShape(32.dp))
                .clip(RoundedCornerShape(32.dp))
                .align(Alignment.Center)
                .padding(horizontal = 4.dp)
                .onFocusChanged { focusState ->
                    searchFocused.value = focusState.hasFocus || focusState.isFocused
                }.background(Color.White),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(searchString.value.isEmpty() && !searchFocused.value){
                        Text(placeholder, color = Color.LightGray.copy(0.7f))
                    }
                    innerTextField()
                }
            },
        )
    }
}