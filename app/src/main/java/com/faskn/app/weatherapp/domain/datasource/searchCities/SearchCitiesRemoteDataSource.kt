package com.faskn.app.weatherapp.domain.datasource.searchCities

import com.algolia.search.saas.Client
import com.algolia.search.saas.Query
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.domain.model.SearchResponse
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesRemoteDataSource @Inject constructor() {

    private val client = Client(Constants.AlgoliaKeys.APPLICATION_ID, Constants.AlgoliaKeys.SEARCH_API_KEY)
    private val index = client.getIndex("cities")

    fun getCityWithQuery(query: String): Single<SearchResponse> {
        return Single.create { single ->
            val algoliaQuery = Query(query)
                .setHitsPerPage(20)

            index.searchAsync(algoliaQuery) { json, _ ->
                val data = Gson().fromJson(json.toString(), SearchResponse::class.java)
                single.onSuccess(data)
            }
        }
    }
}
