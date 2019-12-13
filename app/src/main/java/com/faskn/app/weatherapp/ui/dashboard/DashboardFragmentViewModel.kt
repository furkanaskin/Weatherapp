package com.faskn.app.weatherapp.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.domain.usecase.CurrentWeatherUseCase
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-24
 */

class DashboardFragmentViewModel @Inject internal constructor(private val forecastUseCase: ForecastUseCase, private val currentWeatherUseCase: CurrentWeatherUseCase, var sharedPreferences: SharedPreferences) : BaseViewModel() {

    var forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    var currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> = MutableLiveData()

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val forecastViewState: LiveData<ForecastViewState> = Transformations.switchMap(
        forecastParams
    ) {
        return@switchMap forecastUseCase.execute(it)
    }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> = Transformations.switchMap(
        currentWeatherParams
    ) {
        return@switchMap currentWeatherUseCase.execute(it)
    }
}
