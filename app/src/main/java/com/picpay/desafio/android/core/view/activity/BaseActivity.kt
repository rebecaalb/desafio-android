package com.picpay.desafio.android.core.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.core.view.binding.Binding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), Binding<VB> {

    override var binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize binding
        binding = onCreateViewBinding(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        // clear reference to view binding
        binding = null
    }

    protected infix fun<T> T.whenLifecycleStarted(callback: T.() -> Unit) {
        lifecycleScope.launchWhenStarted { callback(this@whenLifecycleStarted) }
    }

}