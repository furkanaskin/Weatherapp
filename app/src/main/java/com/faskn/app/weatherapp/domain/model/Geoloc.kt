package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Geoloc(

    @field:SerializedName("lng")
    val lng: Double? = null,

    @field:SerializedName("lat")
    val lat: Double? = null
)
