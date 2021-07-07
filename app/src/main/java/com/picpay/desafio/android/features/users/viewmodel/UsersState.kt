package com.picpay.desafio.android.features.users.viewmodel

import com.picpay.desafio.android.feature.users.model.UserUI
import kotlinx.coroutines.flow.MutableStateFlow

class UsersStateImpl : UsersState {
    override val state = MutableStateFlow<UsersState.State>(UsersState.State.Initial)
}

interface UsersState {

    val state: MutableStateFlow<State>

    sealed class State {
        object Initial : State()
        object Loading : State()
        data class Success(val data: List<UserUI>) : State()
        data class Error(val exception: Exception) : State()
    }

    fun MutableStateFlow<State>.initial() {
        value = State.Initial
    }

    fun MutableStateFlow<State>.loading() {
        if (value !is State.Success) value = State.Loading
    }

    fun MutableStateFlow<State>.success(data: List<UserUI>) {
        value = State.Success(data)
    }

    fun MutableStateFlow<State>.error(exception: Exception) {
        if (value !is State.Success) value = State.Error(exception)
    }

}