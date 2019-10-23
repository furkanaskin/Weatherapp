package com.faskn.app.weatherapp.ui.main

import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.utils.domain.Status

/**
 * Created by Furkan on 2019-10-23
 */

class MainActivityViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) {
    fun getForecast() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
