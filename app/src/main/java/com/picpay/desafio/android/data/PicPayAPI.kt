package com.picpay.desafio.android.data

import com.picpay.desafio.android.User
import com.picpay.desafio.android.core.remote.API
import com.picpay.desafio.android.core.remote.CachePolicy
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface PicPayAPI : API {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}