package dev.efantini.timersapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.efantini.timersapp.data.model.CustomTimer
import dev.efantini.timersapp.domain.FormatTimerUseCase
import dev.efantini.timersapp.ui.states.TimerItemUiState
import dev.efantini.timersapp.ui.states.TimerListUiState

/**
 * [ViewModel] for the main view. Uses a [TimerListUiState] to represent the current state of the ui
 * and only has one function that adds a single timer to the list.
 * [addTimer] takes 3 strings as inputs for hours, minutes, seconds of the timer, creates a [CustomTimer],
 * encapsulating it into a [TimerItemUiState], then adds it to the list.
 */
class TimerListViewModel : ViewModel() {

    var timerListContentUiState by mutableStateOf(TimerListUiState())
        private set

    fun addTimer(hours: String, minutes: String, seconds: String) {
        timerListContentUiState = timerListContentUiState.copy(
            timerList = timerListContentUiState.timerList.toMutableList().apply {
                this.add(
                    0,
                    TimerItemUiState(
                        CustomTimer(
                            timerLength = FormatTimerUseCase
                                .formatMillisFromHMSStrings(hours, minutes, seconds),
                            coroutineScope = viewModelScope
                        )
                    )
                )
            }
        )
    }
}
