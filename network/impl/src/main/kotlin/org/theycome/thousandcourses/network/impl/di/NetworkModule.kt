package org.theycome.thousandcourses.network.impl.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.network.api.NetworkDatasourceLocalAnnotation
import org.theycome.thousandcourses.network.impl.NetworkDatasourceLocal
import javax.inject.Singleton

/**
 * Created by Ivan Yakushev on 22.10.2025
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    @NetworkDatasourceLocalAnnotation
    fun provideNetworkDatasourceLocal(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): NetworkDatasource = NetworkDatasourceLocal(context, moshi)
}
