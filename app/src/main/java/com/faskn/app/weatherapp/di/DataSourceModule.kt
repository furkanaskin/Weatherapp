package com.faskn.app.weatherapp.di

import com.faskn.app.weatherapp.db.dao.CitiesForSearchDao
import com.faskn.app.weatherapp.db.dao.CurrentWeatherDao
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.domain.WeatherAppAPI
import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.faskn.app.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideSearchCitiesRemoteDataSource(@Named("PkService") api: WeatherAppAPI) =
        SearchCitiesRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideCurrentWeatherRemoteDataSource(@Named("OwmService") api: WeatherAppAPI) =
        CurrentWeatherRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideForecastRemoteDataSource(@Named("OwmService") api: WeatherAppAPI) =
        ForecastRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideCurrentWeatherLocalDataSource(currentWeatherDao: CurrentWeatherDao) =
        CurrentWeatherLocalDataSource(currentWeatherDao)

    @Provides
    @Singleton
    fun provideForecastLocalDataSource(forecastDao: ForecastDao) =
        ForecastLocalDataSource(forecastDao)

    @Provides
    @Singleton
    fun provideSearchCitiesLocalDataSource(citiesForSearchDao: CitiesForSearchDao) =
        SearchCitiesLocalDataSource(citiesForSearchDao)

}