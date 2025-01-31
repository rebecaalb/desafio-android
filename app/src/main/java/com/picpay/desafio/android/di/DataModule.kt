package com.picpay.desafio.android.di

import android.app.Application
import com.picpay.desafio.android.core.remote.CachePolicyInterceptor
import com.picpay.desafio.android.core.remote.ClientFactory
import com.picpay.desafio.android.core.remote.Environment
import com.picpay.desafio.android.data.PicPayAPI
import com.picpay.desafio.android.data.UserRemoteDataSource
import com.picpay.desafio.android.data.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.UserRepository
import okhttp3.Cache
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.File

object DataModule {

    fun defaultOkHttpClient() = module {
        single {
            val httpCacheDirectory = File(get<Application>().cacheDir, "http_cache")
            ClientFactory.buildOkHttpClient(
                cache = Cache(httpCacheDirectory, ClientFactory.CACHE_SIZE),
                interceptors = listOf(CachePolicyInterceptor())
            )
        }
    }

    fun defaultRetrofit() = module {
        single {
            ClientFactory.buildRetrofit(
                host = Environment.getCurrent().wrapped,
                client = get()
            )
        }
    }

    fun modules() = module {
        single { get<Retrofit>().create(PicPayAPI::class.java) }

        single { UserRemoteDataSource(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
    }

}