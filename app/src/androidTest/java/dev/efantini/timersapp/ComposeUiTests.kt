package dev.efantini.timersapp

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onSiblings
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dev.efantini.timersapp.ui.elements.TimersScreen
import dev.efantini.timersapp.ui.theme.TimersappTheme
import dev.efantini.timersapp.ui.viewmodels.TimerListViewModel
import org.junit.Rule
import org.junit.Test

class ComposeUiTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val button = composeTestRule.onNodeWithText("Start!")
    private val secondsField = composeTestRule.onNodeWithText("Start!")
        .onSiblings()
        .filterToOne(hasText("Seconds"))

    private val minutesField = composeTestRule.onNodeWithText("Start!")
        .onSiblings()
        .filterToOne(hasText("Minutes"))

    private val hoursField = composeTestRule.onNodeWithText("Start!")
        .onSiblings()
        .filterToOne(hasText("Hours"))

    private val listOfTimers = composeTestRule.onNodeWithText("Start!")
        .onParent().onChildAt(5).onChildAt(0)

    @Test
    fun clickingButtonAddsTimer() {
        composeTestRule.setContent {
            TimersappTheme {
                TimersScreen(
                    TimerListViewModel()
                )
            }
        }

        hoursField.performTextInput("1")
        minutesField.performTextInput("59")
        secondsField.performTextInput("59")

        button.performClick()

        listOfTimers.onChildAt(0).assert(hasText("01:59:59"))
    }
}
