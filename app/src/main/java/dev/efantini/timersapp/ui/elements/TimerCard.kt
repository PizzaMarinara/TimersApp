package dev.efantini.timersapp.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import dev.efantini.timersapp.ui.states.TimerItemUiState
import dev.efantini.timersapp.ui.theme.CARD_HEIGHT
import dev.efantini.timersapp.ui.theme.CARD_SHAPE
import dev.efantini.timersapp.ui.theme.FONT_SIZE_MEDIUM
import dev.efantini.timersapp.ui.theme.LIST_ELEMENT_SPACING
import dev.efantini.timersapp.ui.theme.VERTICAL_PADDING_MEDIUM
import dev.efantini.timersapp.ui.theme.VERTICAL_PADDING_SMALL

/**
 * Simple UI Card for a timer item to be shown to the user. Takes a [TimerItemUiState] as a parameter
 * and collects the flow as a state in its main [Text] component.
 */
@Composable
fun TimerCard(
    item: TimerItemUiState,
) {
    val isLightTheme = MaterialTheme.colors.isLight
    Card(
        elevation = CARD_HEIGHT,
        shape = CARD_SHAPE,
        backgroundColor = if (isLightTheme) { Color.LightGray } else { Color.DarkGray }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(LIST_ELEMENT_SPACING))
            Column(Modifier.padding(VERTICAL_PADDING_SMALL)) {
                Text(
                    text = item.timer.timerFlow.collectAsState("").value,
                    style = TextStyle(fontSize = FONT_SIZE_MEDIUM, fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(VERTICAL_PADDING_MEDIUM)
                )
            }
        }
    }
}
