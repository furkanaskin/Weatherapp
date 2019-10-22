package com.faskn.app.weatherapp.domain

import androidx.lifecycle.LiveData
import com.faskn.app.weatherapp.domain.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Furkan on 2019-10-16
 */

interface WeatherAppAPI {

    @GET("forecast")
    fun getForecastByCityName(
        @Query("q")
        city: String
    ): LiveData<ApiResponse<ForecastResponse>>
}
