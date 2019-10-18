package com.faskn.app.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faskn.app.weatherapp.db.dao.ExampleDao
import com.faskn.app.weatherapp.db.entities.Example

@Database(entities = [Example::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
