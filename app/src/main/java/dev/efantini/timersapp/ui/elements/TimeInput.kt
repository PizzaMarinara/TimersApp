package dev.efantini.timersapp.ui.elements

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import dev.efantini.timersapp.ui.theme.FONT_SIZE_SMALL

/**
 * A single input field, with some added logic to only use a numeric keyboard. Receives a lambda that
 * is called whenever the value is changed, delegating the logic to handle it to the parent.
 */
@Composable
fun TimeInput(
    placeholderText: String,
    modifier: Modifier = Modifier,
    state: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = state,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = FONT_SIZE_SMALL)
            )
        },
        modifier = modifier
    )
}
