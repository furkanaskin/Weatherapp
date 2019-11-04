package com.faskn.app.weatherapp.domain.datasource.searchCities

import android.util.Log
import com.algolia.search.saas.places.PlacesClient
import com.algolia.search.saas.places.PlacesQuery
import com.faskn.app.weatherapp.domain.model.SearchResponse
import com.google.gson.Gson
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesRemoteDataSource @Inject constructor(private val client: PlacesClient) {

    fun getCityWithQuery(query: String): Single<SearchResponse> {
        return Single.create { single ->
            val algoliaQuery = PlacesQuery(query)
                .setLanguage("en")
                .setHitsPerPage(25)

            client.searchAsync(algoliaQuery) { json, exception ->
                if (exception == null) {
                    Log.v("qqq", json.toString())
                    val data = Gson().fromJson(json.toString(), SearchResponse::class.java)
                    if (data.hits != null)
                        single.onSuccess(data)
                } else
                    single.onError(UnknownHostException())
            }
        }
    }
}
