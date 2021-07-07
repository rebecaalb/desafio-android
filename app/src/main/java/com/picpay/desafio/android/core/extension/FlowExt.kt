package com.picpay.desafio.android.core.extension

import androidx.lifecycle.LifecycleOwner
import com.picpay.desafio.android.core.utilities.FlowLifecycleObserver
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

inline fun <reified T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    noinline collector: suspend (T) -> Unit
) {
    FlowLifecycleObserver(lifecycleOwner, this, collector)
}