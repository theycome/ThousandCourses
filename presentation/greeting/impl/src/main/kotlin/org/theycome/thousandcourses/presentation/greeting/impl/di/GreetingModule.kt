package org.theycome.thousandcourses.presentation.greeting.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.theycome.thousandcourses.navigator.EntryProviderInstaller
import org.theycome.thousandcourses.navigator.Navigator
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.greeting.api.GreetingKey
import org.theycome.thousandcourses.presentation.greeting.impl.ui.screens.GreetingScreen
import javax.inject.Singleton

/**
 * Created by Ivan Yakushev on 29.12.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object GreetingModule {
    @IntoSet
    @Provides
    @Singleton
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<GreetingKey> { key ->
                GreetingScreen(
                    onEnterKey = {
                        navigator.goTo(CoursesKey.Main)
                        navigator.remove(GreetingKey)
                    },
                )
            }
        }
}
