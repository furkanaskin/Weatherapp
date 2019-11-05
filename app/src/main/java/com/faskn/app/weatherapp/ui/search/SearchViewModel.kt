package com.faskn.app.weatherapp.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.db.entity.CoordEntity
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import com.faskn.app.weatherapp.utils.domain.Resource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchViewModel @Inject internal constructor(private val useCase: SearchCitiesUseCase, private val pref: SharedPreferences) : BaseViewModel() {

    var useCaseParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    private var searchResultLiveData: LiveData<Resource<List<CitiesForSearchEntity>>> = Transformations.switchMap(
        useCaseParams
    ) {
        return@switchMap useCase.execute(it)
    }

    private var searchViewState: LiveData<SearchViewState> = Transformations.map(searchResultLiveData) {
        return@map onSearchResultReady(it)
    }

    fun getSearchViewState() = searchViewState

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            pref.edit().putString("lat", coordEntity.lat.toString()).apply()
            pref.edit().putString("lon", coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
