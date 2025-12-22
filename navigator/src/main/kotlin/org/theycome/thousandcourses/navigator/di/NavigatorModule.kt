package org.theycome.thousandcourses.navigator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.theycome.thousandcourses.navigator.GreetingKey
import org.theycome.thousandcourses.navigator.Navigator
import javax.inject.Singleton

/**
 * Created by Ivan Yakushev on 20.12.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigatorModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = Navigator(GreetingKey)
}
