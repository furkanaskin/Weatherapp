package com.faskn.app.weatherapp.core

import com.faskn.app.weatherapp.BuildConfig

/**
 * Created by Furkan on 2019-10-16
 */

object Constants {

    object NetworkService {
        const val OWM_BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val OWM_API_KEY_VALUE = BuildConfig.OWM_API_KEY_VALUE
        const val OWM_API_KEY_QUERY = "appid"

        const val PK_BASE_URL = "https://api.placekit.co/"
        const val PK_API_KEY_HEADER_PARAM = "x-placekit-api-key"
        const val PK_API_KEY_VALUE = BuildConfig.PK_API_KEY_VALUE
        const val RATE_LIMITER_TYPE = "data"
    }

    object Coords {
        const val LAT = "lat"
        const val LON = "lon"
        const val METRIC = "metric"
    }
}
