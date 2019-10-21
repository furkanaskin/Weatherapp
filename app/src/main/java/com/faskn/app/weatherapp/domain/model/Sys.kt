package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Sys(

    @field:SerializedName("pod")
    val pod: String? = null
)
