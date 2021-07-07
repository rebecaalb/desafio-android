package com.picpay.desafio.android.features.users.activity

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.picpay.desafio.android.core.extension.observe
import com.picpay.desafio.android.core.view.activity.BaseActivity
import com.picpay.desafio.android.core.view.binding.withBinding
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.features.users.adapter.UsersAdapter
import com.picpay.desafio.android.features.users.model.UserUI
import com.picpay.desafio.android.features.users.viewmodel.UsersState
import com.picpay.desafio.android.features.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        const val RECYCLER_VIEW_STATE = "RECYCLER_VIEW_STATE"
    }

    private val viewModel by viewModel<UsersViewModel>()

    private val usersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        viewModel whenLifecycleStarted {
            state.observe(this@MainActivity) { state ->
                when (state) {
                    is UsersState.State.Success -> handleSuccessState(state.data)
                    is UsersState.State.Error -> handleErrorState()
                    UsersState.State.Initial -> handleInitialState()
                    UsersState.State.Loading -> handleLoadingState()
                }
            }
        }

        withBinding {
            recyclerView.apply {
                adapter = usersAdapter
                savedInstanceState?.getParcelable<Parcelable>(RECYCLER_VIEW_STATE)?.let {
                    layoutManager?.onRestoreInstanceState(it)
                }
            }
            retryButton.setOnClickListener { viewModel.loadUsers() }
        }
    }

    override fun onCreateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            RECYCLER_VIEW_STATE,
            binding?.recyclerView?.layoutManager?.onSaveInstanceState()
        )
    }

    private fun handleSuccessState(data: List<UserUI>) = withBinding {
        recyclerView.isVisible = true
        userListProgressBar.isVisible = false
        errorView.isVisible = false
        usersAdapter.users = data
    }

    private fun handleErrorState() = withBinding {
        recyclerView.isVisible = false
        userListProgressBar.isVisible = false
        errorView.isVisible = true
    }

    private fun handleLoadingState() = withBinding {
        recyclerView.isVisible = false
        errorView.isVisible = false
        userListProgressBar.isVisible = true
    }

    private fun handleInitialState() = withBinding {
        usersAdapter.users = emptyList()
        viewModel.loadUsers()
    }

}
