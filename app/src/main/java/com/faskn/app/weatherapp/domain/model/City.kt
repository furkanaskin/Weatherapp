package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(

    @field:SerializedName("country")
    val country: String?,

    @field:SerializedName("coord")
    val coord: Coord?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?
) : Parcelable
