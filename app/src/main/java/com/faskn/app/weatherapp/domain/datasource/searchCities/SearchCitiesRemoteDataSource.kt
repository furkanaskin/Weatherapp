package com.faskn.app.weatherapp.domain.datasource.searchCities

import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.faskn.app.weatherapp.domain.model.SearchRequest
import com.faskn.app.weatherapp.domain.model.SearchResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesRemoteDataSource @Inject constructor(
    private val api: WeatherAppAPI,
) {

    fun getCityWithQuery(request: SearchRequest): Single<SearchResponse> = api.search(request)
}
