package com.faskn.app.weatherapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.faskn.app.weatherapp.domain.model.Wind

/**
 * Created by Furkan on 2019-10-24
 */

@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name = "deg")
    val deg: Double?,
    @ColumnInfo(name = "speed")
    val speed: Double?
) {
    constructor(wind: Wind?) : this(
        deg = wind?.deg,
        speed = wind?.speed
    )
}
