package com.faskn.app.weatherapp.ui.search

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentSearchBinding
import com.faskn.app.weatherapp.di.Injectable
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.faskn.app.weatherapp.utils.extensions.isNetworkAvailable

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(SearchViewModel::class.java), Injectable {
    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initViewModel() {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun init() {
        super.init()

        viewModel.getSearchResultLiveData().observe(
            viewLifecycleOwner,
            Observer {
                // Handle search result
            }
        )

        initSearchView()
    }

    private fun initSearchView() {
        val searchEditText: EditText = mBinding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let { ContextCompat.getColor(it, android.R.color.darker_gray) }
            ?.let { searchEditText.setHintTextColor(it) }
        mBinding.searchView.isActivated = true
        mBinding.searchView.setIconifiedByDefault(false)
        mBinding.searchView.isIconified = false

        val searchViewSearchIcon = mBinding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        mBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    viewModel.getCities(params = SearchCitiesUseCase.SearchCitiesParams(newText, isNetworkAvailable(requireContext())))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true) {
                    viewModel.getCities(SearchCitiesUseCase.SearchCitiesParams(city = newText, fetchRequired = isNetworkAvailable(requireContext())))
                }
                return true
            }
        })
    }
}
