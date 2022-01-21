package dev.efantini.timersapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.efantini.timersapp.ui.elements.TimersScreen
import dev.efantini.timersapp.ui.theme.TimersappTheme
import dev.efantini.timersapp.ui.viewmodels.TimerListViewModel

/**
 * Main activity which simply sets the content to the [TimersScreen] passing
 * a [TimerListViewModel]
 */
class MainActivity : AppCompatActivity() {

    private val timerListViewModel: TimerListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimersappTheme {
                TimersScreen(
                    timerListViewModel
                )
            }
        }
    }
}
