package dev.efantini.timersapp

import app.cash.turbine.test
import dev.efantini.timersapp.data.model.CustomTimer
import java.util.regex.Pattern
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CustomTimerTests {

    @ExperimentalTime
    @Test
    fun `timer works`() = runTest {

        val timerLength: Long = 65000

        /**
         * Testing a timer of 65 seconds.
         * It is asserted that currentTime (0ms at start) + timeRemaining is always equal to our length value.
         */

        CustomTimer(timerLength, this)
            .timerFlow.test(Duration.INFINITE) {
                println("----")
                /**
                 * Print the result of the emitted value from the flow
                 * 65 times (the expected times) and assert each time the emission is equal to the
                 * expected value
                 */
                for (i in 0..(timerLength / 1000)) {
                    awaitItem().also {
                        val arrayInts = Pattern.compile(":").split(it).map { string ->
                            string.toInt()
                        }
                        /**
                         * Timer returns a string already formatted
                         * so it is de-formatted back to a Long value
                         */
                        val timeRemaining = (
                            3600 * 1000 * arrayInts[0] +
                                60 * 1000 * arrayInts[1] +
                                1000 * arrayInts[2]
                            ).toLong()

                        assert(currentTime + timeRemaining == timerLength)
                        println(it)
                    }
                }
                println("----")
                cancelAndIgnoreRemainingEvents()
            }
    }
}
