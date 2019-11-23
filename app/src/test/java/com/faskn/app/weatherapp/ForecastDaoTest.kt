package com.faskn.app.weatherapp

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.dao.ForecastDao
import com.faskn.app.weatherapp.db.entity.CityEntity
import com.faskn.app.weatherapp.db.entity.CoordEntity
import com.faskn.app.weatherapp.db.entity.ForecastEntity
import com.faskn.app.weatherapp.domain.model.*
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

@Config(sdk = [Build.VERSION_CODES.O_MR1])
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
        forecastDao.insertForecast(createSampleForecast(3, "Istanbul"))

        // Then
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityCountry).isEqualTo("Turkey")
        Truth.assertThat(value.city?.cityName).isEqualTo("Istanbul")
    }

    @Test
    fun `insert two forecast to forecast dao and then delete all after this operations count must be 0`() {
        // When
        forecastDao.insertForecast(createSampleForecast(3, "Istanbul"))
        forecastDao.insertForecast(createSampleForecast(4, "Ankara"))

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
        forecastDao.insertForecast(createSampleForecast(1, "Istanbul"))
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(value.city?.cityName).isEqualTo("Istanbul")

        // Then
        forecastDao.updateForecast(createSampleForecast(1, "Ankara"))
        val updatedValue = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(updatedValue.city?.cityName).isEqualTo("Ankara")
    }

    @Test
    fun `delete and insert a forecast`() {
        // When
        forecastDao.insertForecast(createSampleForecast(1, "Istanbul"))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteAndInsert(createSampleForecast(2, "Adana"))
        val newCount = forecastDao.getCount()
        val value = forecastDao.getForecast().getOrAwaitValue()
        Truth.assertThat(newCount).isEqualTo(1)
        Truth.assertThat(value.city?.cityName).isEqualTo("Adana")
    }

    @Test
    fun `first insert a forecast then delete and count must be zero`() {
        // When
        forecastDao.insertForecast(createSampleForecast(1, "Kayseri"))
        val count = forecastDao.getCount()
        Truth.assertThat(count).isEqualTo(1)

        // Then
        forecastDao.deleteForecast(createSampleForecast(1, "Kayseri"))
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

    private fun createSampleForecast(id: Int, cityName: String): ForecastEntity {
        val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
        val weather = listOf(weatherItem)
        val listItem = ListItem(123123, Rain(12.0), "132121", Snow(12.0), weather, Main(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0), Clouds(1), Sys("a"), Wind(12.0, 12.0))
        val list = listOf(listItem)
        return ForecastEntity(id, CityEntity("Turkey", CoordEntity(34.0, 30.0), cityName, 34), list)
    }

    private fun createSampleForecastWithCoord(id: Int, cityName: String, lat: Double, lon: Double): ForecastEntity {
        val list = emptyList<ListItem>()
        return ForecastEntity(id, CityEntity("Turkey", CoordEntity(lon, lat), cityName, 34), list)
    }
}
