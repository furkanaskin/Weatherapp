package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class ListItem(

    @field:SerializedName("dt")
    val dt: Int?,

    @field:SerializedName("rain")
    val rain: Rain?,

    @field:SerializedName("dt_txt")
    val dtTxt: String?,

    @field:SerializedName("snow")
    val snow: Snow?,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>?,

    @field:SerializedName("main")
    val main: Main?,

    @field:SerializedName("clouds")
    val clouds: Clouds?,

    @field:SerializedName("sys")
    val sys: Sys?,

    @field:SerializedName("wind")
    val wind: Wind?
)
