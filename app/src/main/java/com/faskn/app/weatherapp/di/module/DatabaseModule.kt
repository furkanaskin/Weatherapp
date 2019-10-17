package com.faskn.app.weatherapp.di.module

import androidx.room.Room
import android.content.Context
import com.faskn.app.weatherapp.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java, "WeatherApp-DB"
        ).build()
    }
}