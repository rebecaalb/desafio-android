package com.picpay.desafio.android.core.remote

import com.picpay.desafio.android.BuildConfig


enum class Environment(val wrapped: String) {
    PRODUCTION("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"),
    DEV("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/");

    companion object {
        fun getCurrent(): Environment {
            return when(BuildConfig.BUILD_TYPE_API) {
                DEV.name -> DEV
                else -> PRODUCTION
            }
        }
    }

}
