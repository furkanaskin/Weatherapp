package com.faskn.app.weatherapp.dao

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faskn.app.weatherapp.db.WeatherDatabase
import com.faskn.app.weatherapp.db.dao.CitiesForSearchDao
import com.faskn.app.weatherapp.util.generateCitiesForSearchEntity
import com.faskn.app.weatherapp.util.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Furkan on 2019-11-22
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class CitiesForSearchDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var citiesForSearchDao: CitiesForSearchDao

    @Before
    fun setUp() {
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        citiesForSearchDao = weatherDatabase.citiesForSearchDao()
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert one entity and db count must be 1`() {
        // When
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("aushı1h3", "Istanbul"))
        // Then
        val count = citiesForSearchDao.getCount()
        Truth.assertThat(count).isEqualTo(1)
    }

    @Test
    fun `insert two entities and get one of them with city name`() {
        // When
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("kaldsadj", "Ankara"))
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("opıjıwqk", "Istanbul"))

        // Then
        val entityWithSearchQuery = citiesForSearchDao.getCityByName("stan").getOrAwaitValue()
        Truth.assertThat(entityWithSearchQuery.first().id).isEqualTo("opıjıwqk")
    }

    @Test
    fun `insert two entities and get one of them`() {
        // When
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("kaldsadj", "Ankara"))
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("opıjıwqk", "Istanbul"))

        // Then
        val entityWithSearchQuery = citiesForSearchDao.getCities().getOrAwaitValue()
        Truth.assertThat(entityWithSearchQuery.first().id).isEqualTo("kaldsadj")
    }

    @Test
    fun `insert multiple entites and then delete all finally count must be zero`() {
        // When
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("kaldsadj", "Ankara"))
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("opıjıwqk", "Istanbul"))
        citiesForSearchDao.insertCity(generateCitiesForSearchEntity("asdadoasd", "Adana"))

        val count = citiesForSearchDao.getCount()
        Truth.assertThat(count).isEqualTo(3)

        // Then
        citiesForSearchDao.deleteCities()
        val newCount = citiesForSearchDao.getCount()
        Truth.assertThat(newCount).isEqualTo(0)
    }

    @Test
    fun `when is empty count must be 0`() {
        // When
        val count = citiesForSearchDao.getCount()

        // Then
        Truth.assertThat(count).isEqualTo(0)
    }
}
