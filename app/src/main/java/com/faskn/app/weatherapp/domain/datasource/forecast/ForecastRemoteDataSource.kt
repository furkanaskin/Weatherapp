package com.faskn.app.weatherapp.domain.datasource.forecast

import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.faskn.app.weatherapp.domain.model.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getForecastByGeoCords(lat: Double, lon: Double, units: String): Single<ForecastResponse> = api.getForecastByGeoCords(
        lat,
        lon,
        units
    )
}
