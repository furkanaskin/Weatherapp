package com.faskn.app.weatherapp.domain.datasource

import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.model.ForecastResponse
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastLocalDataSource @Inject constructor(private val dao: ForecastDao) {

    fun getForecasyByCityName() = dao.getForecasts()

    fun insertForecast(forecast: ForecastResponse) = dao.insertForecast(ForecastEntity(forecast))
}
