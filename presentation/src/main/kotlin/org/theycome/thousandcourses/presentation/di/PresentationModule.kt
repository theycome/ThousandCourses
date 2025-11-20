package org.theycome.thousandcourses.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesKey
import org.theycome.thousandcourses.presentation.ui.navigation.EntryProviderInstaller
import org.theycome.thousandcourses.presentation.ui.navigation.GreetingKey
import org.theycome.thousandcourses.presentation.ui.navigation.Navigator
import org.theycome.thousandcourses.presentation.ui.navigation.mainCoursesKey
import org.theycome.thousandcourses.presentation.ui.screens.CoursesScreen
import org.theycome.thousandcourses.presentation.ui.screens.GreetingScreen

/**
 * Created by Ivan Yakushev on 12.11.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {
    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<GreetingKey> { key ->
                GreetingScreen(
                    onEnterKey = {
                        navigator.goTo(mainCoursesKey)
                        navigator.remove(GreetingKey)
                    },
                )
            }
            entry<CoursesKey> { key ->
                CoursesScreen(key)
            }
        }
}
