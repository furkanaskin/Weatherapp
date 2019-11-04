package com.faskn.app.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class HitsItem(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("country_code")
    val countryCode: String? = null,

    @field:SerializedName("is_city")
    val isCity: Boolean? = null,

    @field:SerializedName("is_country")
    val isCountry: Boolean? = null,

    @field:SerializedName("administrative")
    val administrative: List<String?>? = null,

    @field:SerializedName("admin_level")
    val adminLevel: Int? = null,

    @field:SerializedName("postcode")
    val postcode: List<String?>? = null,

    @field:SerializedName("county")
    val county: List<String?>? = null,

    @field:SerializedName("_geoloc")
    val geoloc: Geoloc? = null,

    @field:SerializedName("importance")
    val importance: Int? = null,

    @field:SerializedName("objectID")
    val objectID: String? = null,

    @field:SerializedName("is_suburb")
    val isSuburb: Boolean? = null,

    @field:SerializedName("locale_names")
    val localeNames: List<String?>? = null
)
