package org.theycome.thousandcourses.network.api

import arrow.core.raise.Raise
import org.theycome.thousandcourses.network.api.models.Courses
import javax.inject.Qualifier

/**
 * Created by Ivan Yakushev on 20.10.2025
 */
interface NetworkDatasource {
    sealed interface LoadingError {
        data class Exception(
            val cause: String,
        ) : LoadingError

        object EmptyJson : LoadingError

        object UnableToParseJson : LoadingError

        object TimedOut : LoadingError
    }

    context(raise: Raise<LoadingError>)
    suspend fun loadCourses(): Courses
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NetworkDatasourceLocalAnnotation
