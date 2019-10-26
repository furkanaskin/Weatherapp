package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastResponse(

    @field:SerializedName("city")
    val city: City?,

    @field:SerializedName("cnt")
    val cnt: Int?,

    @field:SerializedName("cod")
    val cod: String?,

    @field:SerializedName("message")
    val message: Double?,

    @field:SerializedName("list")
    val list: List<ListItem>?
) : Parcelable
