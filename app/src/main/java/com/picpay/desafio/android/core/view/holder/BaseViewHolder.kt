package com.picpay.desafio.android.core.view.holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : Any, VB : ViewBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: T)

    fun withBinding(action: VB.() -> Unit) {
        binding.run(action)
    }

}