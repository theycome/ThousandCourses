package org.theycome.thousandcourses.core.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Created by Ivan Yakushev on 23.10.2025
 */
class StateFlowHolder<T>(
    init: () -> T,
) {
    private val _flow = MutableStateFlow(init())
    val flow = _flow.asStateFlow()
    val value
        get() = flow.value

    fun update(transform: (T) -> T) {
        _flow.update {
            transform(it)
        }
    }

    fun update(value: T) {
        _flow.update {
            value
        }
    }
}

fun <T> stateFlowHolder(init: () -> T) = StateFlowHolder(init)
