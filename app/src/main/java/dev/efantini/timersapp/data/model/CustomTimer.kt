package dev.efantini.timersapp.data.model

import dev.efantini.timersapp.domain.FormatTimerUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * Model for a custom timer.
 * A [timerLength] and a [coroutineScope] to run it must be passed, other values have a default.
 */
data class CustomTimer(
    val timerLength: Long,
    val coroutineScope: CoroutineScope,
    val timerInterval: Long = 1000,
    val sharingStarted: SharingStarted = SharingStarted.Eagerly
) {

    /**
     * The actual running timer is a Kotlin StateFlow. Simply runs with a for cycle, delaying each
     * emission by a set interval, going down from the maximum length to 0.
     */
    val timerFlow = flow {
        for (elapsedTime in timerLength downTo 0L step timerInterval) {
            emit(FormatTimerUseCase.formatStringFromMillis(elapsedTime))
            if (elapsedTime > 0L) {
                delay(timerInterval)
            }
        }
    }.stateIn(
        scope = coroutineScope,
        started = sharingStarted,
        initialValue = FormatTimerUseCase.formatStringFromMillis(timerLength)
    )
}
