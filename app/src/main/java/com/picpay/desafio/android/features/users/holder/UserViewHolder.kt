package com.picpay.desafio.android.features.users.holder

import android.view.View
import android.view.ViewGroup
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.extension.layoutInflater
import com.picpay.desafio.android.core.view.holder.BaseViewHolder
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.features.users.model.UserUI
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserViewHolder(
    binding: ListItemUserBinding
) : BaseViewHolder<UserUI, ListItemUserBinding>(binding) {

    companion object {
        fun newInstance(parent: ViewGroup): UserViewHolder {
            val binding = ListItemUserBinding.inflate(parent.layoutInflater(), parent, false)
            return UserViewHolder(binding)
        }
    }

    override fun bind(data: UserUI) = withBinding {
        name.text = data.name
        username.text = data.username
        progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(data.img)
            .error(R.drawable.ic_round_account_circle)
            .into(picture, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })
    }
}