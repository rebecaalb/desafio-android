package com.picpay.desafio.android.core.application

import android.app.Application
import com.picpay.desafio.android.di.DataModule
import com.picpay.desafio.android.di.DomainModule
import com.picpay.desafio.android.di.UsersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@PicPayApplication)
            androidLogger(Level.NONE)
            modules(
                DataModule.defaultOkHttpClient(),
                DataModule.defaultRetrofit(),
                DataModule.modules(),
                DomainModule.modules(),
                UsersModule.modules()
            )
        }
    }

}