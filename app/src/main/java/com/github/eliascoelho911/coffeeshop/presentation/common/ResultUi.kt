package com.github.eliascoelho911.coffeeshop.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed class ResultUi<T>

class SuccessUi<T>(val data: T) : ResultUi<T>()

class FailureUi<T>(val throwable: Throwable) : ResultUi<T>()

class LoadingUi<T> : ResultUi<T>()

inline fun <T> ResultUi<T>.on(
    success: (T) -> Unit = {},
    failure: (Throwable) -> Unit = {},
    loading: () -> Unit = {},
) {
    when (this) {
        is SuccessUi<T> -> {
            success(this.data)
        }
        is FailureUi<T> -> {
            failure(this.throwable)
        }
        else -> {
            loading()
        }
    }
}

inline fun <T> resultFlow(crossinline block: suspend FlowCollector<ResultUi<T>>.() -> Unit): Flow<ResultUi<T>> =
    flow {
        emit(LoadingUi())
        block()
    }

suspend fun <T> FlowCollector<ResultUi<T>>.emitSuccess(data: T) {
    emit(SuccessUi(data))
}

suspend fun <T> FlowCollector<ResultUi<T>>.emitFailure(throwable: Throwable) {
    emit(FailureUi(throwable))
}

@Composable
fun <T> Flow<ResultUi<T>>.asState() = collectAsState(initial = LoadingUi())

fun <E, VO> Flow<ResultUi<E>>.convertToVO(convert: (E) -> VO): Flow<ResultUi<VO>> = map {
    when (it) {
        is SuccessUi<E> -> {
            SuccessUi(convert(it.data))
        }
        is FailureUi<E> -> {
            FailureUi(it.throwable)
        }
        else -> {
            LoadingUi()
        }
    }
}