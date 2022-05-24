package com.github.eliascoelho911.coffeeshop.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()

    class Failure<T>(val throwable: Throwable) : Resource<T>()

    class Loading<T> : Resource<T>()
}

inline fun <T> Resource<T>.on(
    success: (T) -> Unit = {},
    failure: (Throwable) -> Unit = {},
    loading: () -> Unit = {},
) {
    when (this) {
        is Resource.Success<T> -> {
            success(this.data)
        }
        is Resource.Failure<T> -> {
            failure(this.throwable)
        }
        else -> {
            loading()
        }
    }
}

inline fun <T> resourceFlow(crossinline block: suspend FlowCollector<Resource<T>>.() -> Unit): Flow<Resource<T>> =
    flow {
        emit(Resource.Loading())
        block()
    }

suspend fun <T> FlowCollector<Resource<T>>.emitSuccess(data: T) {
    emit(Resource.Success(data))
}

suspend fun <T> FlowCollector<Resource<T>>.emitFailure(throwable: Throwable) {
    emit(Resource.Failure(throwable))
}

@Composable
fun <T> Flow<Resource<T>>.asState() = collectAsState(initial = Resource.Loading())