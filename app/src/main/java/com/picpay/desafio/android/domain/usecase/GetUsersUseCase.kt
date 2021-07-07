package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow

class GetUsersUseCase(
    private val repository: UserRepository
) {

    suspend fun execute() = flow {
        emit(repository.getUsers().getOrThrow())
    }

}