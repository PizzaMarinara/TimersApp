package dev.efantini.timersapp.ui.states

/**
 * Ui state for the main screen, combining the state of the inputs and the main list of timers.
 */
data class TimerListUiState(
    val inputs: InputsUiState = InputsUiState(),
    var timerList: MutableList<TimerItemUiState> = mutableListOf()
)
