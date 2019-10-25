package com.faskn.app.weatherapp.db.entity

import androidx.room.*
import com.faskn.app.weatherapp.domain.model.CurrentWeatherResponse
import com.faskn.app.weatherapp.domain.model.WeatherItem
import com.faskn.app.weatherapp.utils.typeconverters.DataConverter

/**
 * Created by Furkan on 2019-10-24
 */

@Entity(tableName = "CurrentWeather")
@TypeConverters(DataConverter::class)
data class CurrentWeatherEntity(
    @ColumnInfo(name = "visibility")
    var visibility: Int?,
    @ColumnInfo(name = "timezone")
    var timezone: Int?,
    @Embedded
    var main: MainEntity?,
    @Embedded
    var clouds: CloudsEntity?,
    @ColumnInfo(name = "dt")
    var dt: Int?,
    @ColumnInfo(name = "weather")
    val weather: List<WeatherItem?>? = null,
    @ColumnInfo(name = "name")
    val name: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "base")
    val base: String?,
    @Embedded
    val wind: WindEntity?
) {
    @Ignore
    constructor(currentWeather: CurrentWeatherResponse) : this(
        visibility = currentWeather.visibility,
        timezone = currentWeather.timezone,
        main = MainEntity(currentWeather.main),
        clouds = CloudsEntity(currentWeather.clouds),
        dt = currentWeather.dt,
        weather = currentWeather.weather,
        name = currentWeather.name,
        id = 0,
        base = currentWeather.base,
        wind = WindEntity(currentWeather.wind)
    )

    fun getCurrentWeather(): WeatherItem? {
        return weather?.first()
    }
}
