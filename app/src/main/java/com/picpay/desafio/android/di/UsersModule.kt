package com.picpay.desafio.android.di

import com.picpay.desafio.android.features.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UsersModule {

    fun modules() = module {
        viewModel { UsersViewModel(get()) }
    }

}