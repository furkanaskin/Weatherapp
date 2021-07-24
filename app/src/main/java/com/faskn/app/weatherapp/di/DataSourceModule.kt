package com.faskn.app.weatherapp.di

import com.algolia.search.saas.places.PlacesClient
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
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRemoteDataSource(api: WeatherAppAPI) =
        CurrentWeatherRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideForecastRemoteDataSource(api: WeatherAppAPI) =
        ForecastRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideSearchCitiesRemoteDataSource(
        client: PlacesClient,
        moshi: Moshi,
    ) = SearchCitiesRemoteDataSource(client, moshi)

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