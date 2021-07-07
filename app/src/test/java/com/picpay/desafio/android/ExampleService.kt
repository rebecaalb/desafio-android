package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayAPI

class ExampleService(
    private val service: PicPayAPI
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}