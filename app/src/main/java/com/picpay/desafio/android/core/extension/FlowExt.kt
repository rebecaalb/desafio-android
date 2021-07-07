package com.picpay.desafio.android.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.catch(action: suspend (exception: Exception) -> Unit): Flow<T> {
    return this.catch { e ->
        if (e is Exception)
            action(e)
        else
            action(Exception())
    }
}