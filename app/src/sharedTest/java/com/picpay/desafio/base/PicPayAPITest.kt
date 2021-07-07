package com.picpay.desafio.base

import com.picpay.desafio.utilities.Mock
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

open class PicPayAPITest: BaseKoinTest() {

    fun getMockWebServerDispatcher(
        codeUsers: Int = 200,
    ) = object : Dispatcher() {

        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().apply {
                when (request.path) {
                    Mock.API.USERS -> {
                        this.apply {
                            setBody(Mock.Data.usersJson)
                            setResponseCode(codeUsers)
                        }
                    }
                    else ->
                        setResponseCode(404)
                }
            }
        }

    }
}