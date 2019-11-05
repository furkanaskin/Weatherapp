package com.faskn.app.weatherapp.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.faskn.app.weatherapp.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-24
 */

class DashboardFragmentViewModel @Inject internal constructor(private val forecastUseCase: ForecastUseCase, private val currentWeatherUseCase: CurrentWeatherUseCase, var sharedPreferences: SharedPreferences) : BaseViewModel() {

    var forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    var currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> = MutableLiveData()
    private val forecastLiveData: LiveData<Resource<ForecastEntity>> = Transformations.switchMap(
        forecastParams
    ) {
        return@switchMap forecastUseCase.execute(it)
    }
    private val currentWeatherLiveData: LiveData<Resource<CurrentWeatherEntity>> = Transformations.switchMap(
        currentWeatherParams
    ) {
        return@switchMap currentWeatherUseCase.execute(it)
    }

    private val forecastViewState: LiveData<ForecastViewState> = Transformations.map(forecastLiveData) {
        return@map onForecastResultReady(it)
    }

    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> = Transformations.map(currentWeatherLiveData) {
        return@map onCurrentWeatherResultReady(it)
    }

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private fun onForecastResultReady(resource: Resource<ForecastEntity>): ForecastViewState {
        return ForecastViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    private fun onCurrentWeatherResultReady(resource: Resource<CurrentWeatherEntity>): CurrentWeatherViewState {
        return CurrentWeatherViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }
}
