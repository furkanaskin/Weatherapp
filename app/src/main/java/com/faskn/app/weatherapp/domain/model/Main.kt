package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Main(

    @field:SerializedName("temp")
    val temp: Double? = null,

    @field:SerializedName("temp_min")
    val tempMin: Double? = null,

    @field:SerializedName("grnd_level")
    val grndLevel: Double? = null,

    @field:SerializedName("temp_kf")
    val tempKf: Double? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("pressure")
    val pressure: Double? = null,

    @field:SerializedName("sea_level")
    val seaLevel: Double? = null,

    @field:SerializedName("temp_max")
    val tempMax: Double? = null
)
