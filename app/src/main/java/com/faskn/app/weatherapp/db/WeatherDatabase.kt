package com.faskn.app.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.faskn.app.weatherapp.db.dao.CitiesForSearchDao
import com.faskn.app.weatherapp.db.dao.CurrentWeatherDao
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.db.entity.CitiesForSearchEntity
import com.faskn.app.weatherapp.db.entity.CurrentWeatherEntity
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.utils.typeconverters.DataConverter

@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao
}
