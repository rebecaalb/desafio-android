package com.picpay.desafio.android.features.users.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.feature.users.adapter.UserListDiffCallback
import com.picpay.desafio.android.feature.users.holder.UserViewHolder
import com.picpay.desafio.android.feature.users.model.UserUI

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var users = emptyList<UserUI>()
        set(value) {
            val result = DiffUtil.calculateDiff(UserListDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

}