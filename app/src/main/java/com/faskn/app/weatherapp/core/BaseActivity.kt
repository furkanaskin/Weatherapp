package com.faskn.app.weatherapp.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Furkan on 2019-10-16
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(
    viewModelClass: Class<VM>
) : AppCompatActivity() {

    protected lateinit var binding: DB

    val viewModel: VM by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes()) as DB
        initViewModel(viewModel)
        setupBindingLifecycleOwner()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */

    abstract fun initViewModel(viewModel: VM)

    private fun setupBindingLifecycleOwner() {
        binding.lifecycleOwner = this
    }
}
