package com.picpay.desafio.android.features.users.mappers

import com.picpay.desafio.android.core.extension.orInvalidNumber
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.features.users.model.UserUI

fun User.toUI(): UserUI {
    return UserUI(
        img = img.orEmpty(),
        name = name.orEmpty(),
        id = id.orInvalidNumber(),
        username = username.orEmpty()
    )
}