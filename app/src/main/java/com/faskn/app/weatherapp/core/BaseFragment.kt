package com.faskn.app.weatherapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

/**
 * Created by Furkan on 2019-10-16
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(
    @LayoutRes layoutResId: Int,
    viewModelClass: Class<VM>
) : Fragment(layoutResId) {

    protected lateinit var binding: DB
        private set

    private fun init(view: View) {
        binding = DataBindingUtil.bind(view)!!
        with(binding) {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }

    open fun init() {}

    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(super.onCreateView(inflater, container, savedInstanceState)!!)
        init()
        return binding.root
    }

    open fun refresh() {}

    open fun navigate(action: Int) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }
}
