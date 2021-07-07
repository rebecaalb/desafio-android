package com.picpay.desafio.android.core.remote

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface API

suspend fun <T, A : API> A.executeRequest(request: suspend A.() -> T): Result<T> {
    return try {
        Result.success(request())
    } catch (exception: Exception) {
        val networkException = exception.toNetworkException()
        Result.failure(networkException)
    }
}

private fun Throwable.toNetworkException(): Exception {
    return when (this) {
        is HttpException -> {
            when (code()) {
                in 400..499 -> Exception("", this) // ClientException
                in 500..599 -> Exception("", this) // ServerException
                else -> UnexpectedException() // UnexpectedException
            }
        }
        is SocketTimeoutException -> Exception("", this) // TimeoutException
        is UnknownHostException -> Exception("", this) // ConnectionException
        is ConnectException -> Exception("", this) // ConnectionException
        is SocketException -> Exception("", this) // CanceledException
        is CancellationException -> Exception("", this) // CanceledException
        else -> UnexpectedException() // UnexpectedException
    }
}

data class UnexpectedException(
    override val message: String = "The server response is unexpected"
): Exception(message)