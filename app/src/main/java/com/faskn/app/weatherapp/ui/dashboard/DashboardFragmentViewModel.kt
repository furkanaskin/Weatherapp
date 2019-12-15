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

    private val _forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    val forecastParams: LiveData<ForecastUseCase.ForecastParams> get() = _forecastParams

    private val _currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> = MutableLiveData()
    val currentWeatherParams: LiveData<CurrentWeatherUseCase.CurrentWeatherParams> get() = _currentWeatherParams

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val forecastViewState: LiveData<ForecastViewState> = Transformations.switchMap(
        _forecastParams
    ) {
        return@switchMap forecastUseCase.execute(it)
    }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> = Transformations.switchMap(
        _currentWeatherParams
    ) {
        return@switchMap currentWeatherUseCase.execute(it)
    }

    fun setForecastParams(params: ForecastUseCase.ForecastParams) {
        if (_forecastParams.value == params)
            return
        _forecastParams.value = params
    }

    fun setCurrentWeatherParams(params: CurrentWeatherUseCase.CurrentWeatherParams) {
        if (_currentWeatherParams.value == params)
            return
        _currentWeatherParams.value = params
    }
}
