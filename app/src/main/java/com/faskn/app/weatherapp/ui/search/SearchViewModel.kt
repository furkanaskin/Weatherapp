package com.faskn.app.weatherapp.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.db.entity.CoordEntity
import com.faskn.app.weatherapp.domain.usecase.SearchCitiesUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchViewModel @Inject internal constructor(private val useCase: SearchCitiesUseCase, private val pref: SharedPreferences) : BaseViewModel() {

    var useCaseParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()

    fun getSearchViewState() = searchViewState

    private var searchViewState: LiveData<SearchViewState> = Transformations.switchMap(
            useCaseParams
    ) {
        return@switchMap useCase.execute(it)
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            pref.edit().putString(Constants.Coords.LAT, coordEntity.lat.toString()).apply()
            pref.edit().putString(Constants.Coords.LON, coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}
