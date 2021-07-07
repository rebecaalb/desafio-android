package com.picpay.desafio.android.data

import com.picpay.desafio.android.core.remote.API
import com.picpay.desafio.android.core.remote.CachePolicy
import com.picpay.desafio.android.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface PicPayAPI : API {

    @Headers(CachePolicy.cacheAlways)
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}