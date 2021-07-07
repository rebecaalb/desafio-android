package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remote: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> =
        remote.fetchUsers()

}