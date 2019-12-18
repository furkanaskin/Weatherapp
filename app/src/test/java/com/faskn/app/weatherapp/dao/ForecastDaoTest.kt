package com.faskn.app.weatherapp.dao

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.util.createSampleForecastResponse
import com.faskn.app.weatherapp.util.createSampleForecastWithCoord
import com.faskn.app.weatherapp.util.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-11-21
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class ForecastDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var forecastDao: ForecastDao

    @Before
    fun setUp() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.forecastDao()
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert a forecast to forecast dao`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(3, "Istanbul"))

        // Then
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityCountry).isEqualTo("Turkey")
        Truth.assertThat(value.city?.cityName).isEqualTo("Istanbul")
    }

    @Test
    fun `insert two forecast to forecast dao and then delete all after this operations count must be 0`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(3, "Istanbul"))
        forecastDao.insertForecast(createSampleForecastResponse(4, "Ankara"))

        val value = forecastDao.getCount()
        Truth.assertThat(value).isEqualTo(2)

        // Then
        forecastDao.deleteAll()
        val newValue = forecastDao.getCount()
        Truth.assertThat(newValue).isEqualTo(0)
    }

    @Test
    fun `insert a forecast and then update`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, "Istanbul"))
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityName).isEqualTo("Istanbul")

        // Then
        forecastDao.updateForecast(createSampleForecastResponse(1, "Ankara"))
        val updatedValue = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(updatedValue.city?.cityName).isEqualTo("Ankara")
    }

    @Test
    fun `delete and insert a forecast`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, "Istanbul"))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteAndInsert(createSampleForecastResponse(2, "Adana"))
        val newCount = forecastDao.getCount()
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(newCount).isEqualTo(1)
        Truth.assertThat(value.city?.cityName).isEqualTo("Adana")
    }

    @Test
    fun `first insert a forecast then delete and count must be zero`() {
        // When
        forecastDao.insertForecast(createSampleForecastResponse(1, "Kayseri"))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteForecast(createSampleForecastResponse(1, "Kayseri"))
        val newCount = forecastDao.getCount()
        Truth.assertThat(newCount).isEqualTo(0)
    }

    @Test
    fun `first insert a forecast and then get it with coords`() {
        // When
        forecastDao.insertForecast(createSampleForecastWithCoord(1, "Adana", 1.0, 2.0))
        forecastDao.insertForecast(createSampleForecastWithCoord(3, "Kayseri", 10.0, 30.0))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(2)

        // Then
        val value = forecastDao.getForecastByCoord(10.0, 30.0).getOrAwaitValue()
        Truth.assertThat(value.city?.cityName).isEqualTo("Kayseri")
    }
}
