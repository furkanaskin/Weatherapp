package com.faskn.app.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faskn.app.weatherapp.db.dao.CurrentWeatherDao
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.db.entity.ForecastEntity

@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class
    ],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao
}
