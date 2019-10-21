package com.faskn.app.weatherapp.domain.mapper

import com.faskn.app.weatherapp.domain.model.ForecastResponse
import com.faskn.app.weatherapp.domain.model.ListItem
import com.faskn.app.weatherapp.utils.Mapper
import javax.inject.Inject

/**
 * Created by Furkan on 2019-10-21
 */

class ForecastMapper @Inject constructor() : Mapper<ForecastResponse, List<ListItem>> {
    override fun map(type: ForecastResponse): List<ListItem> {
        return type.list ?: emptyList()
    }
}
