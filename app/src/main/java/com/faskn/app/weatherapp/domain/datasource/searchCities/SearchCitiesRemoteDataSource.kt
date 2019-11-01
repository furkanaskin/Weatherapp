package com.faskn.app.weatherapp.domain.datasource.searchCities

import com.algolia.search.saas.places.PlacesClient
import com.algolia.search.saas.places.PlacesQuery
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.domain.model.SearchResponse
import com.google.gson.Gson
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesRemoteDataSource @Inject constructor() {

    fun getCityWithQuery(query: String): Single<SearchResponse> {
        val client = PlacesClient(Constants.AlgoliaKeys.APPLICATION_ID, Constants.AlgoliaKeys.SEARCH_API_KEY)

        return Single.create { single ->
            val algoliaQuery = PlacesQuery(query)
                .setLanguage("en")
                .setHitsPerPage(20)

            client.searchAsync(algoliaQuery) { json, exception ->
                if (exception == null) {
                    val data = Gson().fromJson(json.toString(), SearchResponse::class.java)
                    single.onSuccess(data)
                } else
                    single.onError(UnknownHostException())
            }
        }
    }
}
