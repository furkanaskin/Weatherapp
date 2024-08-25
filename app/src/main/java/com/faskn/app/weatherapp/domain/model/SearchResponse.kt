package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "maxResults")
    val maxResults: Int? = null,
    @Json(name = "resultsCount")
    val resultsCount: Int? = null,
    @Json(name = "query")
    val query: String? = null,
    @Json(name = "results")
    val results: List<ResultsItem?>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ResultsItem(
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "countrycode")
    val countryCode: String? = null,
    @Json(name = "county")
    val county: String? = null,
    @Json(name = "coordinates")
    val coordinates: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "population")
    val population: Int? = null,
    @Json(name = "maxResults")
    val zipcode: List<String?>? = null,
    @Json(name = "highlight")
    val highlight: String? = null,
    @Json(name = "citycode")
    val cityCode: String? = null,
    @Json(name = "administrative")
    val administrative: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "administrativecode")
    val administrativeCode: String? = null,
    @Json(name = "lat")
    val lat: Double? = null,
    @Json(name = "lng")
    val lng: Double? = null
) : Parcelable
