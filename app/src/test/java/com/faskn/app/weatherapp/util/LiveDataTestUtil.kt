package com.faskn.app.weatherapp.util

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.faskn.app.weatherapp.db.entity.*
import com.faskn.app.weatherapp.domain.model.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Created by Furkan on 2019-11-21
 */

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

// Data Generators
fun createSampleForecastResponse(id: Int, cityName: String): ForecastEntity {
    val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
    val weather = listOf(weatherItem)
    val listItem = ListItem(
        123123, Rain(12.0), "132121", Snow(12.0), weather,
        Main(
            34.0,
            30.0,
            2.0,
            321.0,
            21,
            132.0,
            12.0,
            35.0
        ),
        Clouds(1), Sys("a"), Wind(12.0, 12.0)
    )
    val list = listOf(listItem)
    return ForecastEntity(id, CityEntity("Turkey", CoordEntity(34.0, 30.0), cityName, 34), list)
}

fun createSampleForecastWithCoord(id: Int, cityName: String, lat: Double, lon: Double): ForecastEntity {
    val list = emptyList<ListItem>()
    return ForecastEntity(id, CityEntity("Turkey", CoordEntity(lon, lat), cityName, 34), list)
}

fun generateCitiesForSearchEntity(id: String, name: String): CitiesForSearchEntity {
    return CitiesForSearchEntity("Clear", "Turkey", CoordEntity(34.0, 30.0), name, "Beyoglu", 1, id)
}

fun generateCurrentWeatherEntity(name: String, id: Int): CurrentWeatherEntity {
    val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
    val weather = listOf(weatherItem)
    return CurrentWeatherEntity(1, 2, MainEntity(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0), null, 3421399123, weather, name, id, "Celciues", null)
}

fun createSampleForecastResponse(): ForecastResponse {
    val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
    val weather = listOf(weatherItem)
    val listItem = ListItem(
        123123, Rain(12.0), "132121", Snow(12.0), weather,
        Main(
            34.0,
            30.0,
            2.0,
            321.0,
            21,
            132.0,
            12.0,
            35.0
        ),
        Clouds(1), Sys("a"), Wind(12.0, 12.0)
    )
    val list = listOf(listItem)
    return ForecastResponse(
        City("Turkey", Coord(32.32, 30.30), "Istanbul", 10),
        null,
        null,
        null,
        list
    )
}

fun createSampleCurrentWeatherResponse(): CurrentWeatherResponse {
    val weatherItem = WeatherItem("12d", "clouds", "cloud & sun", 1)
    val weather = listOf(weatherItem)
    return CurrentWeatherResponse(
        null, null, Main(34.0, 30.0, 2.0, 321.0, 21, 132.0, 12.0, 35.0),
        Clouds(
            1
        ),
        Sys("a"), null, Coord(32.32, 30.30), weather, "Istanbul", null, 10, null, null
    )
}

fun generateSampleSearchCitiesResponse(): SearchResponse {
    return SearchResponse(
        listOf(
            HitsItem(
                "Turkey", null, isCity = true, isCountry = false,
                administrative = listOf(
                    "İstanbul"
                ),
                adminLevel = null, postcode = null, county = listOf("Beyoğlu"), geoloc = null, importance = null, objectID = "10", isSuburb = null, localeNames = null
            )
        )
    )
}
