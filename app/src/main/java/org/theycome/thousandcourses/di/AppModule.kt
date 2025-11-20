package org.theycome.thousandcourses.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.theycome.thousandcourses.presentation.ui.navigation.GreetingKey
import org.theycome.thousandcourses.presentation.ui.navigation.Navigator
import javax.inject.Singleton

/**
 * Created by Ivan Yakushev on 12.11.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator(GreetingKey)
}
