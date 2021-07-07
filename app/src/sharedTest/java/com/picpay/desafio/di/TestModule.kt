package com.picpay.desafio.di

import com.picpay.desafio.android.core.remote.CachePolicyInterceptor
import com.picpay.desafio.android.core.remote.ClientFactory
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

object TestModule {

    fun mockedClient(mockWebServer: MockWebServer) = module {

        single {
            ClientFactory.buildRetrofit(
                mockWebServer.url("/").toString(),
                ClientFactory.buildOkHttpClient(
                    interceptors = listOf(
                        CachePolicyInterceptor()
                    )
                )
            )
        }
    }

}