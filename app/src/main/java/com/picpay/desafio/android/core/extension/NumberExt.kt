package com.picpay.desafio.android.core.extension

const val invalidIntNumber: Int = -1


fun Int?.orInvalidNumber() = this ?: invalidIntNumber