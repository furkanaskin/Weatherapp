package com.faskn.app.weatherapp.ui.splash

import android.util.Log
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.entities.Example
import com.faskn.app.weatherapp.service.WeatherAppAPI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-16
 */

class SplashActivityViewModel @Inject constructor(api: WeatherAppAPI, weatherDatabase: WeatherDatabase) : BaseViewModel(api, weatherDatabase) {


}