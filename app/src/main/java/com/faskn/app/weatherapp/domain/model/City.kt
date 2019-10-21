package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class City(

    @field:SerializedName("country")
    val country: String?,

    @field:SerializedName("coord")
    val coord: Coord?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?
)
