package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(

    @field:SerializedName("deg")
    val deg: Double?,

    @field:SerializedName("speed")
    val speed: Double?
) : Parcelable
