package com.faskn.app.weatherapp.repo

import com.faskn.app.weatherapp.domain.datasource.ForecastDataSource
import com.faskn.app.weatherapp.domain.model.ForecastResponse
import com.faskn.app.weatherapp.utils.domain.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastRepository @Inject constructor(private val forecastDataSource: ForecastDataSource) {

    fun getForecastByCityName(city: String): Observable<Resource<ForecastResponse>> =
        forecastDataSource.getForecasyByCityName(city)
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
}
