package com.faskn.app.weatherapp.domain.datasource

import com.faskn.app.weatherapp.domain.WeatherAppAPI
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getForecasyByCityName(city: String) = api.getForecastByCityName(city)
}
