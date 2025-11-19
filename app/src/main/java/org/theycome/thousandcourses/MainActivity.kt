package org.theycome.thousandcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import org.theycome.thousandcourses.presentation.ui.navigation.EntryProviderInstaller
import org.theycome.thousandcourses.presentation.ui.navigation.Navigator
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme
import javax.inject.Inject

// Hypothesis 12 - crash happens on rendering second screen - maybe it's something with compound navigation key
// Hypothesis 13 - pairing CoursesKey with TryScreen - OK
// Hypothesis 14 - use `if` instead of `when` when dispatching on CoursesKey seems not to crash (at least that often)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryProviderScopes: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ThousandCoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        backStack = navigator.backStack,
                        modifier = Modifier.padding(innerPadding),
                        onBack = { navigator.goBack() },
                        entryProvider =
                            entryProvider {
                                entryProviderScopes.forEach { provider -> provider() }
                            },
                    )
                }
            }
        }
    }
}
