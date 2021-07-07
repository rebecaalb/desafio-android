package com.picpay.desafio.android.core.extension

import android.view.LayoutInflater
import android.view.View

fun View.layoutInflater(): LayoutInflater = LayoutInflater.from(context)