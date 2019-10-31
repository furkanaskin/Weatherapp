package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class HighlightResult(

    @field:SerializedName("country")
    val country: Country? = null,

    @field:SerializedName("name")
    val name: Name? = null
)
