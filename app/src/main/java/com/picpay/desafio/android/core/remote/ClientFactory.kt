package com.picpay.desafio.android.core.remote

import com.picpay.desafio.android.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientFactory {

    const val CACHE_SIZE = (10 * 1024 * 1024).toLong()

    fun buildOkHttpClient(
        cache: Cache? = null,
        interceptors: List<Interceptor?> = emptyList(),
        networkInterceptors: List<Interceptor?> = emptyList(),
        builder: OkHttpClient.Builder.() -> Unit = { }
    ): OkHttpClient = OkHttpClient.Builder().apply {

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }.also { addInterceptor(it) }
        }

        interceptors.filterNotNull().forEach { interceptor -> addInterceptor(interceptor) }
        networkInterceptors.filterNotNull().forEach { addNetworkInterceptor(it) }
        cache(cache)
        apply(builder)
    }.build()

    /**
     * Creates a instance of [Retrofit] using the provided [client] and use [GsonConverterFactory]
     * as converter factory.
     * */
    fun buildRetrofit(
        host: String,
        client: OkHttpClient,
    ) = Retrofit.Builder().apply {
        baseUrl(host)
        addConverterFactory(GsonConverterFactory.create())
        client(client)
    }.build()

}