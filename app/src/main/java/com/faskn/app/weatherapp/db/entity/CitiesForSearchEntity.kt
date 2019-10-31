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
    @ColumnInfo(name = "Name")
    var name: String? = null,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    var id: Int? = null
) : Parcelable {
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        coord = hitsItem?.coord?.let { CoordEntity(it) },
        name = hitsItem?.name,
        id = hitsItem?.id
    )
}
