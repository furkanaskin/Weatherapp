package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Country(

    @field:SerializedName("matchLevel")
    val matchLevel: String? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)
