package com.faskn.app.weatherapp.ui.search

import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.utils.domain.Status

/**
 * Created by Furkan on 2019-10-31
 */

class SearchViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CitiesForSearchEntity>? = null
) {
    fun getSearchResult() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
