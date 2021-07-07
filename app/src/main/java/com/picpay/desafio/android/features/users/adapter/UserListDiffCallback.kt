package com.picpay.desafio.android.features.users.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.features.users.model.UserUI

class UserListDiffCallback(
    private val oldList: List<UserUI>,
    private val newList: List<UserUI>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}