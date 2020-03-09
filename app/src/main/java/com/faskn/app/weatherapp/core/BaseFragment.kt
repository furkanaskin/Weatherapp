package com.faskn.app.weatherapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.AndroidInjection

/**
 * Created by Furkan on 2019-10-16
 */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes val layout: Int, viewModelClass: Class<VM>) : Fragment() {

    open lateinit var binding: DB
    lateinit var dataBindingComponent: DataBindingComponent
    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
    }

    open fun init() {}

    private val viewModel by lazy {
        (activity as? BaseActivity<*, *>)?.viewModelProviderFactory?.let { ViewModelProvider(this, it).get(viewModelClass) }
    }

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        init()
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    open fun refresh() {}

    open fun navigate(action: Int) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }
}
