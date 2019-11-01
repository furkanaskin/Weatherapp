package com.faskn.app.weatherapp.db.entity

import android.os.Parcelable
import androidx.room.*
import com.faskn.app.weatherapp.domain.model.HitsItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "CitiesForSearch")
data class CitiesForSearchEntity(
    @ColumnInfo(name = "Country")
    val country: String?,
    @Embedded
    val coord: CoordEntity?,
    @ColumnInfo(name = "fullName")
    val name: String?,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
) : Parcelable {
    @Ignore
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        coord = CoordEntity(hitsItem?.geoloc),
        name = hitsItem?.county?.first().plus(", ") + hitsItem?.administrative?.first().plus(", ") + hitsItem?.country,
        id = hitsItem?.objectID.toString()
    )
}
