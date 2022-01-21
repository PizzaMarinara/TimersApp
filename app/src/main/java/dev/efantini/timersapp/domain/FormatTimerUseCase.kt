package dev.efantini.timersapp.domain

import java.util.concurrent.TimeUnit

/**
 * A formatting Object that operates some static transformations on [Long] values to [String]
 * and vice versa.
 */
object FormatTimerUseCase {

    fun formatStringFromMillis(milliseconds: Long): String {

        val hours = (TimeUnit.MILLISECONDS.toHours(milliseconds))
            .toString().padStart(2, '0')

        val minutes = (TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60)
            .toString().padStart(2, '0')

        val seconds = (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60)
            .toString().padStart(2, '0')

        return "$hours:$minutes:$seconds"
    }

    fun formatMillisFromHMSStrings(hours: String, minutes: String, seconds: String): Long {

        return TimeUnit.HOURS.toMillis(formatStringToLong(hours)) +
            TimeUnit.MINUTES.toMillis(formatStringToLong(minutes)) +
            TimeUnit.SECONDS.toMillis(formatStringToLong(seconds))
    }

    private fun formatStringToLong(string: String): Long {
        return if (string.isBlank() || string.any { !Character.isDigit(it) })
            0 else
            string.toLong()
    }
}
