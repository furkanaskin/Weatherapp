package com.faskn.app.weatherapp.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.faskn.app.weatherapp.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-26
 */

class WeatherDetailViewModel @Inject constructor(private val forecastLocalDataSource: ForecastLocalDataSource) : BaseViewModel() {

    var weatherItem: ObservableField<ListItem> = ObservableField()
    private var forecastLiveData: LiveData<ForecastEntity> = MutableLiveData()
    var selectedDayDate: String? = null
    var selectedDayForecastLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()
    fun getForecastLiveData() = forecastLiveData

    init {
        getForecast()
    }

    private fun getForecast(): LiveData<ForecastEntity> {
        forecastLiveData =
            Transformations.switchMap(
                forecastLocalDataSource.getForecast()
            ) {
                val forecastLiveData = MutableLiveData<ForecastEntity>()
                forecastLiveData.value = it
                return@switchMap forecastLiveData
            }

        return forecastLiveData
    }
}
