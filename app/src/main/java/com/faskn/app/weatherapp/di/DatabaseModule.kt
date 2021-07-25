package com.faskn.app.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.dao.CitiesForSearchDao
import com.faskn.app.weatherapp.db.dao.CurrentWeatherDao
import com.faskn.app.weatherapp.db.dao.ForecastDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "WeatherApp-DB"
        ).build()

    @Provides
    @Singleton
    fun provideForecastDao(db: WeatherDatabase): ForecastDao = db.forecastDao()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db: WeatherDatabase): CurrentWeatherDao = db.currentWeatherDao()

    @Provides
    @Singleton
    fun provideCitiesForSearchDao(db: WeatherDatabase): CitiesForSearchDao = db.citiesForSearchDao()

}