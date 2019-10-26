package com.faskn.app.weatherapp.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.faskn.app.weatherapp.core.BaseViewModel
import com.faskn.app.weatherapp.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-26
 */

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
