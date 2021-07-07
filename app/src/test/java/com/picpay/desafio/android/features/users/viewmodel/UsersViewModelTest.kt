package com.picpay.desafio.android.features.users.viewmodel

import android.os.Build
import com.picpay.desafio.utilities.Mock
import com.picpay.desafio.base.PicPayAPITest
import com.picpay.desafio.android.core.remote.UnexpectedException
import com.picpay.desafio.android.features.users.mappers.toUI
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(JUnit4::class)
class UsersViewModelTest: PicPayAPITest() {

    private val viewModel: UsersViewModel by inject()

    @Test
    fun loadUsersStateSuccess() = runBlocking {
        // given
        mockWebServer.dispatcher = getMockWebServerDispatcher(200)
        val usersState = UsersState.State.Success(Mock.Data.users.map { it.toUI() })

        // when
        viewModel.loadUsers()
        viewModel.state
            .take(3)
            .collect {}

        // then
        assertEquals(usersState, viewModel.state.value)
    }

    @Test
    fun loadUsersStateError() = runBlocking {
        // given
        mockWebServer.dispatcher = getMockWebServerDispatcher(400)

        // when
        viewModel.loadUsers()
        viewModel.state
            .take(3)
            .collect {}

        // then
        assertEquals(UsersState.State.Error(UnexpectedException()), viewModel.state.value)
    }

}