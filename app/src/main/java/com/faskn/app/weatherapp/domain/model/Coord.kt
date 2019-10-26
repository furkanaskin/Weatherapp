package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coord(

    @field:SerializedName("lon")
    val lon: Double?,

    @field:SerializedName("lat")
    val lat: Double?
) : Parcelable
