package com.faskn.app.weatherapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rain(

    @field:SerializedName("3h")
    val jsonMember3h: Double?
) : Parcelable
