package com.faskn.app.weatherapp.ui.weather_detail

import androidx.databinding.ObservableField
import com.faskn.app.weatherapp.core.BaseViewModel
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-26
 */

class WeatherDetailViewModel @Inject constructor() : BaseViewModel() {
    var backgroundColor: ObservableField<Int> = ObservableField()
}
