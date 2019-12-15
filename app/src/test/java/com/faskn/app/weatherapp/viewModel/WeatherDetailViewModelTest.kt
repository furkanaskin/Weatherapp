package com.faskn.app.weatherapp.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.domain.datasource.forecast.ForecastLocalDataSource
import com.faskn.app.weatherapp.domain.model.*
import com.faskn.app.weatherapp.ui.weather_detail.WeatherDetailViewModel
import com.faskn.app.weatherapp.util.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-12-15
 */
@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class WeatherDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel
    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var forecastDao: ForecastDao
    private lateinit var forecastLocalDataSource: ForecastLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.forecastDao()
        forecastLocalDataSource = ForecastLocalDataSource(forecastDao)
        weatherDetailViewModel = WeatherDetailViewModel(forecastLocalDataSource)
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert forecast and when getForecastLiveData called the livedata result must be Forecastentity`() {
        // When
        forecastLocalDataSource.insertForecast(createSampleForecast())

        // Then
        val result = weatherDetailViewModel.getForecastLiveData().getOrAwaitValue()
        Truth.assertThat(result.city?.cityName).isEqualTo("Istanbul")
    }

    private fun createSampleForecast(): ForecastResponse {
        val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
        val weather = listOf(weatherItem)
        val listItem = ListItem(123123, Rain(12.0), "132121", Snow(12.0), weather, Main(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0), Clouds(1), Sys("a"), Wind(12.0, 12.0))
        val list = listOf(listItem)
        return ForecastResponse(City("Turkey", Coord(32.32, 30.30), "Istanbul", 1), null, null, null, list)
    }
}
