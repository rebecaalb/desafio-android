package com.picpay.desafio.android.data

import android.os.Build
import com.picpay.desafio.base.PicPayAPITest
import com.picpay.desafio.android.domain.repository.UserRepository
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(JUnit4::class)
class UserRepositoryTest: PicPayAPITest() {

    private val repository: UserRepository by inject()

    @Test
    fun hasCacheInterceptorHeader() = runBlocking {
        // given
        mockWebServer.dispatcher = getMockWebServerDispatcher(200)

        // when
        repository.getUsers()

        // then
        val request = mockWebServer.takeRequest()
        assertTrue(request.headers["Cache-control"] != null)
    }

}