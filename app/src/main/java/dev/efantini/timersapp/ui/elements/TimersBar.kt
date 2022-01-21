package dev.efantini.timersapp.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import dev.efantini.timersapp.R
import dev.efantini.timersapp.ui.theme.INPUTS_HORIZONTAL_SPACING
import dev.efantini.timersapp.ui.viewmodels.TimerListViewModel

/**
 * The main UI bar with [TextField]s and the [Button] to add timers to the list.
 */
@Composable
fun TimersBar(viewModel: TimerListViewModel) {

    val focusManager = LocalFocusManager.current

    /**
     * Simple function that properly formats the inputs from the [TextField]s, if any digit isn't
     * a number then the input is invalid, so it's completely truncated. Otherwise, eventual leading
     * zeros are also trimmed. (eg. the input 003 is converted to simply 3)
     */
    val trimLeadingZeros: (String) -> String = { str ->
        when {
            str.isBlank() || str.any { !Character.isDigit(it) } -> {
                ""
            }
            else -> {
                str.trimStart {
                    it == "0".single()
                }
            }
        }
    }

    var hours: String
        by rememberSaveable { mutableStateOf(viewModel.timerListContentUiState.inputs.hours) }

    var minutes: String
        by rememberSaveable { mutableStateOf(viewModel.timerListContentUiState.inputs.minutes) }

    var seconds: String
        by rememberSaveable { mutableStateOf(viewModel.timerListContentUiState.inputs.seconds) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(INPUTS_HORIZONTAL_SPACING)
    ) {
        TimeInput(
            placeholderText = stringResource(id = R.string.hours),
            modifier = Modifier.weight(1f),
            state = hours
        ) { str -> hours = trimLeadingZeros(str) }
        TimeInput(
            placeholderText = stringResource(id = R.string.minutes),
            modifier = Modifier.weight(1f),
            state = minutes
        ) { str -> minutes = trimLeadingZeros(str) }
        TimeInput(
            placeholderText = stringResource(id = R.string.seconds),
            modifier = Modifier.weight(1f),
            state = seconds
        ) { str -> seconds = trimLeadingZeros(str) }
        Button(
            onClick = {
                /**
                 * Note: here you could check if the inputs are "valid" (eg. at least one is not empty)
                 * but since it's not specified, clicking the button without any input from
                 * the TextInputs simply returns a timer that starts at 0.
                 * Currently adding an empty timer is allowed because there's no specification for it.
                 */
                viewModel.addTimer(hours, minutes, seconds)
                hours = ""
                minutes = ""
                seconds = ""
                focusManager.clearFocus(true)
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(id = R.string.start_button))
        }
    }
}
