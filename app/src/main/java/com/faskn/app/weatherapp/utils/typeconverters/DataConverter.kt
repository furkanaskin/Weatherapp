package com.faskn.app.weatherapp.utils.typeconverters

import androidx.room.TypeConverter
import com.faskn.app.weatherapp.domain.model.ListItem
import com.faskn.app.weatherapp.domain.model.WeatherItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Furkan on 2019-10-21
 */

object DataConverter {

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<ListItem> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<ListItem>>() {
        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(objects: List<ListItem>): String {
        return Gson().toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun weatherStringToList(data: String?): List<WeatherItem> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<WeatherItem>>() {
        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun weatherListToString(objects: List<WeatherItem>): String {
        return Gson().toJson(objects)
    }
}
