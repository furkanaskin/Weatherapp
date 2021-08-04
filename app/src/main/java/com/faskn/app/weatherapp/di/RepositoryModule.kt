package com.faskn.app.weatherapp.di

import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastRemoteDataSource
import com.faskn.app.weatherapp.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.faskn.app.weatherapp.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.faskn.app.weatherapp.repo.CurrentWeatherRepository
import com.faskn.app.weatherapp.repo.ForecastRepository
import com.faskn.app.weatherapp.repo.SearchCitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
        currentWeatherLocalDataSource: CurrentWeatherLocalDataSource,
    ) = CurrentWeatherRepository(currentWeatherRemoteDataSource, currentWeatherLocalDataSource)

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRemoteDataSource: ForecastRemoteDataSource,
        forecastLocalDataSource: ForecastLocalDataSource,
    ) = ForecastRepository(forecastRemoteDataSource, forecastLocalDataSource)

    @Provides
    @Singleton
    fun provideSearchCitiesRepository(
        searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource,
        searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
    ) = SearchCitiesRepository(searchCitiesRemoteDataSource, searchCitiesLocalDataSource)

}