package org.theycome.thousandcourses.network.impl

import android.content.Context
import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.core.raise.ensureNotNull
import com.squareup.moshi.Moshi
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.network.api.models.Courses
import java.io.BufferedReader
import kotlin.io.readText
import kotlin.jvm.java

/**
 * Created by Ivan Yakushev on 22.10.2025
 */
class NetworkDatasourceLocal(
    // TODO extract presentation module
    val context: Context,
    val moshi: Moshi,
) : NetworkDatasource {
    context(raise: Raise<NetworkDatasource.LoadingError>)
    override suspend fun loadCourses(): Courses =
        catch({
            val lines =
                context.resources
                    .openRawResource(R.raw.courses)
                    .bufferedReader()
                    .use(BufferedReader::readText)
                    .ifEmpty { raise.raise(NetworkDatasource.LoadingError.EmptyJson) }

            val courses =
                moshi
                    .adapter(Courses::class.java)
                    .fromJson(lines)

            raise.ensureNotNull(courses) {
                raise.raise(NetworkDatasource.LoadingError.UnableToParseJson)
            }
        }) {
            raise.raise(NetworkDatasource.LoadingError.Exception(it.toString()))
        }
}
