package com.faskn.app.weatherapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.faskn.app.weatherapp.db.entity.ForecastEntity

@Dao
interface ForecastDao {

    @Query("SELECT * FROM Forecast")
    fun getForecasts(): LiveData<List<ForecastEntity>>

    @Query("SELECT * FROM Forecast WHERE city = :cityName")
    fun getForecastByCityName(cityName: String): LiveData<ForecastEntity>

    @Insert(onConflict = REPLACE)
    fun insertForecast(forecast: ForecastEntity)

    @Update()
    fun updateForecast(forecast: ForecastEntity)

    @Delete
    fun deleteForecast(forecast: ForecastEntity)

    @Query("Select count(*) from Forecast")
    fun getCount(): Int
}
