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

// Hypothesis 8 - get rid of inner returning function in Inputs - nope
// Hypothesis 9 - the culprit is capturing remembered nullable variables in local variables and
//                then passing them upstream which causes inner Compose error - NOPE
// Hypothesis 10 - place callUpstreamCall to the very end of Composable function - NOPE
// Hypothesis 11 - create payload using double bang in a separate function at the end of Composable
//                 works.. (after 20 attempts)
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
