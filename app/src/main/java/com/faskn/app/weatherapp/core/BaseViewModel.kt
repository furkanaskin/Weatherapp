package com.faskn.app.weatherapp.core

import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.domain.WeatherAppAPI

/**
 * Created by Furkan on 2019-10-16
 */

open class BaseViewModel(var baseApi: WeatherAppAPI? = null, var db: WeatherDatabase? = null) : AutoDisposeViewModel()
