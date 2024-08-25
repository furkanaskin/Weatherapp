package com.faskn.app.weatherapp.db.entity

import android.os.Parcelable
import android.text.SpannableString
import androidx.room.*
import com.faskn.app.weatherapp.domain.model.ResultsItem
import com.faskn.app.weatherapp.utils.extensions.bold
import com.faskn.app.weatherapp.utils.extensions.italic
import com.faskn.app.weatherapp.utils.extensions.plus
import com.faskn.app.weatherapp.utils.extensions.spannable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "CitiesForSearch")
data class CitiesForSearchEntity(
    @ColumnInfo(name = "administrative")
    val administrative: String?,
    @ColumnInfo(name = "Country")
    val country: String?,
    @Embedded
    val coord: CoordEntity?,
    @ColumnInfo(name = "fullName")
    val name: String?,
    @ColumnInfo(name = "county")
    val county: String?,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
) : Parcelable {

    @Ignore
    constructor(result: ResultsItem?) : this(
        country = result?.country,
        administrative = result?.administrative,
        coord = CoordEntity(result?.lat, result?.lng),
        name = result?.name,
        county = result?.county,
        id = result?.coordinates.toString()
    )

    fun getFullName(): SpannableString {
        return spannable {
            bold(name ?: "").plus(", ") +
                    bold(county ?: "").plus(", ") +
                    italic(administrative ?: "").plus(", ") +
                    italic(country ?: "")
        }
    }
}
