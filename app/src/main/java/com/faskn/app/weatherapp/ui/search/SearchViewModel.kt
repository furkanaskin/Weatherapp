package com.faskn.app.weatherapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchViewModel @Inject internal constructor(private val useCase: SearchCitiesUseCase) : BaseViewModel() {

    private var searchResultLiveData: LiveData<SearchViewState> = MutableLiveData()
    fun getSearchResultLiveData() = searchResultLiveData

    fun getCities(params: SearchCitiesUseCase.SearchCitiesParams): LiveData<SearchViewState> {
        searchResultLiveData =
            Transformations.switchMap(
                useCase.execute(params)
            ) {
                val searchResultLiveData = MutableLiveData<SearchViewState>()
                searchResultLiveData.value = onSearchResultReady(it)
                return@switchMap searchResultLiveData
            }

        return searchResultLiveData
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }
}
