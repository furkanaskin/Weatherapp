package com.faskn.app.weatherapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import java.lang.reflect.ParameterizedType

/**
 * Created by Furkan on 2019-10-16
 */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes val layout: Int,
    viewModelClass: Class<VM>
) : Fragment() {

    protected lateinit var binding: DB
    lateinit var dataBindingComponent: DataBindingComponent
    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
    }

    open fun init() {}

    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    open fun onInject() {}

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
