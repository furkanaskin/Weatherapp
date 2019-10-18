package com.faskn.app.weatherapp.ui.main

import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.service.WeatherAppAPI
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(api: WeatherAppAPI, weatherDatabase: WeatherDatabase) : BaseViewModel(api, weatherDatabase) {
    fun getAppName() = "WeatherApp"
}
