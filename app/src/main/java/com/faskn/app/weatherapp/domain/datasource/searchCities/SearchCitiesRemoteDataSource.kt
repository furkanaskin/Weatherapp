package com.faskn.app.weatherapp.domain.datasource.searchCities

import com.algolia.search.saas.places.PlacesClient
import com.algolia.search.saas.places.PlacesQuery
import com.faskn.app.weatherapp.domain.model.SearchResponse
import com.faskn.app.weatherapp.utils.extensions.tryCatch
import com.squareup.moshi.Moshi
import io.reactivex.Single
import javax.inject.Inject
import timber.log.Timber

/**
 * Created by Furkan on 2019-10-31
 */

class SearchCitiesRemoteDataSource @Inject constructor(
    private val client: PlacesClient,
    private val moshi: Moshi
) {

    fun getCityWithQuery(query: String): Single<SearchResponse> {
        return Single.create { single ->
            val algoliaQuery = PlacesQuery(query)
                .setLanguage("en")
                .setHitsPerPage(25)

            client.searchAsync(algoliaQuery) { json, exception ->
                if (exception == null) {
                    tryCatch(
                        tryBlock = {
                            val adapter = moshi.adapter<SearchResponse>(SearchResponse::class.java)
                            val data = adapter.fromJson(json.toString())

                            if (data?.hits != null) {
                                single.onSuccess(data)
                            }
                        },
                        catchBlock = {
                            Timber.e(it, it.localizedMessage)
                        }
                    )
                } else {
                    single.onError(Throwable("Can't find '$query'. Please try another one."))
                }
            }
        }
    }
}
