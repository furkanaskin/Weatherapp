package com.faskn.app.weatherapp.domain

import com.faskn.app.weatherapp.domain.model.CurrentWeatherResponse
import com.faskn.app.weatherapp.domain.model.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Furkan on 2019-10-16
 */

interface WeatherAppAPI {

    @GET("forecast")
    fun getForecastByCityName(
        @Query("q")
        city: String,
        @Query("units")
        units: String
    ): Single<ForecastResponse>

    @GET("weather")
    fun getCurrentByCityName(
        @Query("q")
        city: String,
        @Query("units")
        units: String
    ): Single<CurrentWeatherResponse>
}
