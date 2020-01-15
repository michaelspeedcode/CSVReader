package com.speed.mvvm.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<VM : BaseViewModel?> : AppCompatActivity() {
    @JvmField
    protected var viewModel: VM? = null
    protected abstract fun createViewModel(): VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }
}