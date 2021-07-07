package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

object DomainModule {
    fun modules() = module {
        factory { GetUsersUseCase(get()) }
    }
}