package org.theycome.thousandcourses.navigator

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 * Created by Ivan Yakushev on 20.12.2025
 */
typealias EntryProviderInstaller = EntryProviderScope<NavKey>.() -> Unit

@ActivityRetainedScoped
class Navigator(
    startDestination: NavKey,
) {
    val backStack: SnapshotStateList<NavKey> = mutableStateListOf(startDestination)

    fun goTo(destination: NavKey) {
        if (backStack.lastOrNull() != destination) {
            backStack.add(destination)
        }
    }

    fun remove(destination: NavKey) {
        backStack.remove(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }

    fun log() =
        println(
            "=== back stack ===\n" +
                backStack.map { it }.joinToString("\n") +
                "\n===\n",
        )
}

fun NavBackStack<NavKey>.log() =
    println(
        "=== back stack ===\n" +
            map { it }.joinToString("\n") +
            "\n===\n",
    )
