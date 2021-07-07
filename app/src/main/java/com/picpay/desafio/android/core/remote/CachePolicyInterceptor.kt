package com.picpay.desafio.android.core.remote

import com.picpay.desafio.android.core.remote.CachePolicyInterceptor.Companion.cachePolicyHeader
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CachePolicyInterceptor: Interceptor {

    companion object {
        const val cachePolicyHeader = "cache-policy"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()
        val request = currentRequest.header(cachePolicyHeader)?.let {
            currentRequest.cache()
        } ?: run {
            currentRequest
        }
        return chain.proceed(request)
    }

    private fun Request.cache(): Request {
        val cacheControl = CacheControl.Builder()
            .maxStale(2, TimeUnit.HOURS)
            .build()
        return newBuilder()
            .removeHeader(cachePolicyHeader)
            .cacheControl(cacheControl)
            .build()
    }

}

object CachePolicy {
    const val cacheAlways = "$cachePolicyHeader:cacheAlways"
}