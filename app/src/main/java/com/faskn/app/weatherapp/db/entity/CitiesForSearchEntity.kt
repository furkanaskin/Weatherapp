package com.faskn.app.weatherapp.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.faskn.app.weatherapp.domain.model.HitsItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "CitiesForSearch")
data class CitiesForSearchEntity(
    @ColumnInfo(name = "Country")
    var country: String? = null,
    @Embedded
    var coord: CoordEntity? = null,
    @ColumnInfo(name = "fullName")
    var name: String? = null,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    var id: String
) : Parcelable {
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        coord = CoordEntity(hitsItem?.geoloc),
        name = hitsItem?.county?.first().plus(", ") + hitsItem?.administrative?.first().plus(", ") + hitsItem?.country,
        id = hitsItem?.objectID.toString()
    )
}
