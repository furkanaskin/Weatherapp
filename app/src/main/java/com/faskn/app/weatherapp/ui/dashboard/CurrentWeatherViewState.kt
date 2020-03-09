package com.faskn.app.weatherapp.ui.dashboard

import com.faskn.app.weatherapp.core.BaseViewState
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.utils.domain.Status

/**
 * Created by Furkan on 2019-10-24
 */

class CurrentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
