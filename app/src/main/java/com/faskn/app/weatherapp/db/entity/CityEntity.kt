package com.faskn.app.weatherapp.db.entity

import androidx.room.*
import com.faskn.app.weatherapp.domain.model.City

/**
 * Created by Furkan on 2019-10-22
 */

@Entity(tableName = "City")
data class CityEntity(
    @ColumnInfo(name = "cityCountry")
    var cityCountry: String?,
    @Embedded
    var cityCoord: CoordEntity?,
    @ColumnInfo(name = "cityName")
    var cityName: String?,
    @PrimaryKey
    @ColumnInfo(name = "cityId")
    var cityId: Int?
) {

    @Ignore
    constructor(city: City) : this(
        cityId = city.id,
        cityCoord = city.coord?.let { CoordEntity(it) },
        cityName = city.name,
        cityCountry = city.country
    )
}
