package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Clouds(

    @field:SerializedName("all")
    val all: Int? = null
)
