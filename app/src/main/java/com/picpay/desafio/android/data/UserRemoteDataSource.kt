package com.picpay.desafio.android.data

import com.picpay.desafio.android.core.remote.UnexpectedException
import com.picpay.desafio.android.core.remote.executeRequest
import com.picpay.desafio.android.domain.model.User

class UserRemoteDataSource(
    private val api: PicPayAPI
) {
    suspend fun fetchUsers(): Result<List<User>> {
        return api.executeRequest {
            getUsers()
        }.mapCatching {
            if (it.isSuccessful)
                it.body().orEmpty()
            else
                throw UnexpectedException()
        }
    }
}