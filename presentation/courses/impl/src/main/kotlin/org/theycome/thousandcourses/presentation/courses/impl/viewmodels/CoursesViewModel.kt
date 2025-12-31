package org.theycome.thousandcourses.presentation.courses.impl.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.raise.recover
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.theycome.thousandcourses.core.di.DefaultDispatcher
import org.theycome.thousandcourses.core.utils.StateFlowHolder
import org.theycome.thousandcourses.core.utils.stateFlowHolder
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.network.api.NetworkDatasourceLocalAnnotation
import org.theycome.thousandcourses.presentation.courses.api.models.CoursesModel
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/**
 * Created by Ivan Yakushev on 22.10.2025
 */
@HiltViewModel
class CoursesViewModel
    @Inject
    constructor(
        @param:DefaultDispatcher
        private val dispatcher: CoroutineDispatcher,
        @param:NetworkDatasourceLocalAnnotation
        private val networkDatasource: NetworkDatasource,
    ) : ViewModel() {
        val coursesFlow: StateFlowHolder<CoursesModel> = stateFlowHolder { CoursesModel.Loading }
        val loadingCoursesErrorFlow: StateFlowHolder<NetworkDatasource.LoadingError?> = stateFlowHolder { null }

        fun loadCourses() {
            viewModelScope.launch(dispatcher) {
                withTimeoutOrNull(10.seconds) {
                    recover({
                        val courses = networkDatasource.loadCourses()
                        CoursesModel
                            .Success(courses)
                            .let(coursesFlow::update)
                    }) { loadingCoursesErrorFlow.update(it) }
                } ?: loadingCoursesErrorFlow.update(NetworkDatasource.LoadingError.TimedOut)
            }
        }
    }
