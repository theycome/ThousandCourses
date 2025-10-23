package org.theycome.thousandcourses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.raise.recover
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.theycome.thousandcourses.core.di.DefaultDispatcher
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.network.api.NetworkDatasourceLocalAnnotation
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/**
 * Created by Ivan Yakushev on 22.10.2025
 */
@HiltViewModel
class CoursesViewModel
    @Inject
    constructor(
        @DefaultDispatcher
        private val dispatcher: CoroutineDispatcher,
        @param:NetworkDatasourceLocalAnnotation
        private val networkDatasource: NetworkDatasource,
    ) : ViewModel() {
        fun loadCourses() {
            viewModelScope.launch(dispatcher) {
                withTimeoutOrNull(10.seconds) {
                    recover({
                        networkDatasource.loadCourses()
                    }) {
                    }
                }
            }
        }
    }
