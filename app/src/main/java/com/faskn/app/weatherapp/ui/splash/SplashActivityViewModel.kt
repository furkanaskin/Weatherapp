package com.faskn.app.weatherapp.ui.splash

import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.service.WeatherAppAPI
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-16
 */

class SplashActivityViewModel @Inject constructor(api: WeatherAppAPI, weatherDatabase: WeatherDatabase) : BaseViewModel(api, weatherDatabase)
