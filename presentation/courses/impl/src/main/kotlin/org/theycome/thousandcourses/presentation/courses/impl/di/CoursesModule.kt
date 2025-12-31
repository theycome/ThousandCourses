package org.theycome.thousandcourses.presentation.courses.impl.di

import androidx.compose.runtime.Composable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.theycome.thousandcourses.navigator.EntryProviderInstaller
import org.theycome.thousandcourses.navigator.Navigator
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.CoursesScreenStateful
import javax.inject.Singleton

/**
 * Created by Ivan Yakushev on 12.11.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object CoursesModule {
    @IntoSet
    @Provides
    @Singleton
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            val block: @Composable (CoursesKey) -> Unit = { key ->
                CoursesScreenStateful(key)
            }
            entry<CoursesKey.Main> { key ->
                block(key)
            }
            entry<CoursesKey.Account> { key ->
                block(key)
            }
            entry<CoursesKey.Favorites> { key ->
                block(key)
            }
        }
}
