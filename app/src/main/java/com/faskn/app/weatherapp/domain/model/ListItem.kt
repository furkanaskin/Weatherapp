package com.faskn.app.weatherapp.domain.model

import android.graphics.Color
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle

@Parcelize
data class ListItem(

    @field:SerializedName("dt")
    val dt: Long?,

    @field:SerializedName("rain")
    val rain: Rain?,

    @field:SerializedName("dt_txt")
    val dtTxt: String?,

    @field:SerializedName("snow")
    val snow: Snow?,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>?,

    @field:SerializedName("main")
    val main: Main?,

    @field:SerializedName("clouds")
    val clouds: Clouds?,

    @field:SerializedName("sys")
    val sys: Sys?,

    @field:SerializedName("wind")
    val wind: Wind?
) : Parcelable {
    fun getWeatherItem(): WeatherItem? {
        return weather?.first()
    }

    fun getDay(): String? {
        return dt?.let { getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault()) }
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHourColor(): Int {
        return when (dtTxt?.substringAfter(" ")?.substringBeforeLast(":")) {
            "00:00" -> Color.parseColor("#28E0AE")
            "03:00" -> Color.parseColor("#FF0090")
            "06:00" -> Color.parseColor("#FFAE00")
            "09:00" -> Color.parseColor("#0090FF")
            "12:00" -> Color.parseColor("#DC0000")
            "15:00" -> Color.parseColor("#0051FF")
            "18:00" -> Color.parseColor("#3D28E0")
            "21:00" -> Color.parseColor("#50E3FE")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }
}
