package com.faskn.app.weatherapp.ui.main

import android.util.Log
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.faskn.app.weatherapp.domain.usecase.ForecastUseCase
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(api: WeatherAppAPI, weatherDatabase: WeatherDatabase, private val useCase: ForecastUseCase) : BaseViewModel(api, weatherDatabase) {

    fun test() {
        useCase.execute(ForecastUseCase.ForecastParams(city = "Istanbul,tr"))
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(this)
            .subscribe {
                Log.v("qqq", it.data.toString())
                Log.v("qqq", it.error.toString())
            }
    }
}
