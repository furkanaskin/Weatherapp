package com.faskn.app.weatherapp.ui.search

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentSearchBinding
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.di.Injectable
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.faskn.app.weatherapp.ui.main.MainActivity
import com.faskn.app.weatherapp.ui.search.result.SearchResultAdapter
import com.faskn.app.weatherapp.utils.extensions.hideKeyboard
import com.faskn.app.weatherapp.utils.extensions.observeWith
import com.faskn.app.weatherapp.utils.extensions.tryCatch

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(SearchViewModel::class.java), Injectable {

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initSearchResultsAdapter()
        initSearchView()

        viewModel.getSearchViewState().observeWith(
            viewLifecycleOwner
        ) {
            mBinding.viewState = it
            it.data?.let { results -> initSearchResultsRecyclerView(results) }
        }
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
                if (newText.isNotEmpty() && newText.count() > 2) {
                    viewModel.setSearchParams(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true && newText.count() > 2) {
                    viewModel.setSearchParams(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return true
            }
        })
    }

    private fun initSearchResultsAdapter() {
        val adapter = SearchResultAdapter { item ->
            item.coord?.let {
                viewModel.saveCoordsToSharedPref(it)
                    ?.subscribe { _, _ ->

                        tryCatch(
                            tryBlock = {
                                mBinding.searchView.hideKeyboard((activity as MainActivity))
                            }
                        )

                        findNavController().navigate(R.id.action_searchFragment_to_dashboardFragment)
                    }
            }
        }

        mBinding.recyclerViewSearchResults.adapter = adapter
        mBinding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initSearchResultsRecyclerView(list: List<CitiesForSearchEntity>) {
        (mBinding.recyclerViewSearchResults.adapter as SearchResultAdapter).submitList(list.distinctBy { it.getFullName() }.sortedBy { it.importance })
    }
}
