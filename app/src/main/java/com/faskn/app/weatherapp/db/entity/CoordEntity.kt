package com.faskn.app.weatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.faskn.app.weatherapp.domain.model.Coord
import kotlinx.parcelize.Parcelize

/**
 * Created by Furkan on 2019-10-22
 */

@Parcelize
@Entity(tableName = "Coord")
data class CoordEntity(
    @ColumnInfo(name = "lat")
    val lat: Double?,
    @ColumnInfo(name = "lon")
    val lon: Double?,
) : Parcelable {

    @Ignore
    constructor(coord: Coord) : this(
        lat = coord.lat,
        lon = coord.lon
    )
}
