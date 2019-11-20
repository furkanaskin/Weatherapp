package com.faskn.app.weatherapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitsItem(

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "country_code")
    val countryCode: String? = null,

    @Json(name = "is_city")
    val isCity: Boolean? = null,

    @Json(name = "is_country")
    val isCountry: Boolean? = null,

    @Json(name = "administrative")
    val administrative: List<String?>? = null,

    @Json(name = "admin_level")
    val adminLevel: Int? = null,

    @Json(name = "postcode")
    val postcode: List<String?>? = null,

    @Json(name = "county")
    val county: List<String?>? = null,

    @Json(name = "_geoloc")
    val geoloc: Geoloc? = null,

    @Json(name = "importance")
    val importance: Int? = null,

    @Json(name = "objectID")
    val objectID: String? = null,

    @Json(name = "is_suburb")
    val isSuburb: Boolean? = null,

    @Json(name = "locale_names")
    val localeNames: List<String?>? = null
)
