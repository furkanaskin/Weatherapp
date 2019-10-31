package com.faskn.app.weatherapp.ui.search

import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentSearchBinding
import com.faskn.app.weatherapp.di.Injectable

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(SearchViewModel::class.java), Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()

        viewModel.getSearchResultLiveData().observe(
            viewLifecycleOwner,
            Observer {
                // Handle search result
            }
        )
    }
}
