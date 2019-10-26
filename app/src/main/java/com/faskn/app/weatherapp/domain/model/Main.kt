package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Main(

    @field:SerializedName("temp")
    val temp: Double?,

    @field:SerializedName("temp_min")
    val tempMin: Double?,

    @field:SerializedName("grnd_level")
    val grndLevel: Double?,

    @field:SerializedName("temp_kf")
    val tempKf: Double?,

    @field:SerializedName("humidity")
    val humidity: Int?,

    @field:SerializedName("pressure")
    val pressure: Double?,

    @field:SerializedName("sea_level")
    val seaLevel: Double?,

    @field:SerializedName("temp_max")
    val tempMax: Double?
) {
    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return humidity.toString() + "°"
    }

    fun getTempMinString(): String {
        return tempMin.toString().substringBefore(".") + "°"
    }

    fun getTempMaxString(): String {
        return tempMax.toString().substringBefore(".") + "°"
    }
}
