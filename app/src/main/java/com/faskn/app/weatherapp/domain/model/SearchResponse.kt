package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("hits")
    val hits: List<HitsItem?>? = null
)
