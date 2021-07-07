package com.picpay.desafio.android.core.view.binding

import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding

interface Binding<VB : ViewBinding> {

    var binding: VB?

    /**
     * Generates the [ViewBinding] instance.
     * **/
    fun onCreateViewBinding(inflater: LayoutInflater): VB {
        throw NotImplementedError("onCreateViewBinding must be implemented")
    }

    /**
     * Generates the [ViewBinding] instance.
     * **/
    fun onCreateViewBinding(view: View): VB {
        throw NotImplementedError("onCreateViewBinding must be implemented")
    }

}

fun <VB : ViewBinding> Binding<VB>.withBinding(action: VB.() -> Unit) {
    binding?.let(action)
}