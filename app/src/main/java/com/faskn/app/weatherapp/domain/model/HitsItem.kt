package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class HitsItem(

    @field:SerializedName("_highlightResult")
    val highlightResult: HighlightResult? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("coord")
    val coord: Coord? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("objectID")
    val objectID: String? = null
)
