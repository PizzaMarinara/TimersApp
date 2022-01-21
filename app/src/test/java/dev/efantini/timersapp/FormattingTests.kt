package dev.efantini.timersapp

import dev.efantini.timersapp.domain.FormatTimerUseCase
import org.junit.Test

class FormattingTests {
    @Test
    fun `millis values are formatted correctly to a string`() {
        val milliseconds: Long = 7199000 // 1 hour, 59 min, 59 sec
        val formattedString = FormatTimerUseCase.formatStringFromMillis(milliseconds)

        /**
         * Leading zeros are always added to one-digit values.
         */
        assert(formattedString == "01:59:59")

        assert(formattedString != "1:59:59")
    }

    @Test
    fun `HMS values are formatted correctly to milliseconds`() {
        val hours = "1"
        val minutes = "59"
        val seconds = "59" // 7199000 milliseconds
        val formattedLong = FormatTimerUseCase.formatMillisFromHMSStrings(
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )
        assert(formattedLong == (7199000).toLong())
    }
}
