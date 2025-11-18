package org.theycome.thousandcourses.presentation.di

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import org.theycome.thousandcourses.presentation.ui.navigation.EntryProviderInstaller
import org.theycome.thousandcourses.presentation.ui.navigation.GreetingKey
import org.theycome.thousandcourses.presentation.ui.navigation.Navigator
import org.theycome.thousandcourses.presentation.ui.navigation.TryKey
import org.theycome.thousandcourses.presentation.ui.screens.GreetingScreenTryFullDesign

/**
 * Created by Ivan Yakushev on 12.11.2025
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object PresentationModule {
    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<GreetingKey> { key ->
                GreetingScreenTryFullDesign(
                    onEnterKey = {
                        navigator.goTo(TryKey)
                        navigator.remove(GreetingKey)
                    },
                )
            }
            // TODO - add CoursesKey
            entry<TryKey> { key ->
                Column {
                    Text("TryKey")
                }
            }
        }
}
