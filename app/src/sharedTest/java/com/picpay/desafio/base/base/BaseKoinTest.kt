package com.picpay.desafio.base.base

import com.picpay.desafio.di.TestModule
import com.picpay.desafio.android.di.DataModule
import com.picpay.desafio.android.di.DomainModule
import com.picpay.desafio.android.di.UsersModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule

open class BaseKoinTest: AutoCloseKoinTest() {

    val mockWebServer = MockWebServer()

    @get:Rule
    open val koinTestRule = KoinTestRule.create {
        modules(
            listOf(
                module {
                    single { MockWebServer() }
                },
                TestModule.mockedClient(mockWebServer),
                DataModule.modules(),
                DomainModule.modules(),
                UsersModule.modules()
            )
        )
    }

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

}