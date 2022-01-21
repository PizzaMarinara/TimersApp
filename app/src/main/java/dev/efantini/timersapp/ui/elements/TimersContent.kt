package dev.efantini.timersapp.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.efantini.timersapp.R
import dev.efantini.timersapp.ui.states.TimerListUiState
import dev.efantini.timersapp.ui.theme.PADDING_MEDIUM
import dev.efantini.timersapp.ui.theme.VERTICAL_ELEMENT_SPACING
import dev.efantini.timersapp.ui.theme.VERTICAL_ELEMENT_SPACING_SMALL
import dev.efantini.timersapp.ui.viewmodels.TimerListViewModel

/**
 * The main composable element for the only screen of this sample app. Takes a [TimerListViewModel]
 * as a parameter, passing it over to the [TimersBar] and using the items of the [TimerListUiState] as
 * list items for the [LazyColumn].
 */
@Composable
fun TimersScreen(viewModel: TimerListViewModel) {

    Surface {
        Column(modifier = Modifier.padding(PADDING_MEDIUM)) {
            TimersBar(viewModel)
            Spacer(modifier = Modifier.height(VERTICAL_ELEMENT_SPACING))
            Text(stringResource(id = R.string.running_timers_list_title))
            Spacer(modifier = Modifier.height(VERTICAL_ELEMENT_SPACING_SMALL))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(viewModel.timerListContentUiState.timerList) { timer ->
                    Divider()
                    TimerCard(timer)
                }
            }
        }
    }
}
