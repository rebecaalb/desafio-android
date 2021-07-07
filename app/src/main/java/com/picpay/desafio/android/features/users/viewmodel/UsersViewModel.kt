package com.picpay.desafio.android.features.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.extension.catch
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.feature.users.mappers.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel(), UsersState by UsersStateImpl() {

    fun loadUsers() = viewModelScope.launch(Dispatchers.IO) {
        getUsersUseCase.execute()
            .onStart { state.loading() }
            .map { list -> list.map { it.toUI() } }
            .onEach { state.success(it) }
            .catch { state.error(it) }
            .launchIn(this)
    }

}