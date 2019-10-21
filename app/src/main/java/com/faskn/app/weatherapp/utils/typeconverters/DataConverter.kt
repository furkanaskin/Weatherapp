package com.faskn.app.weatherapp.utils.typeconverters

import androidx.room.TypeConverter
import com.faskn.app.weatherapp.domain.model.City
import com.faskn.app.weatherapp.domain.model.ListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Furkan on 2019-10-21
 */

object DataConverter {

    @TypeConverter
    @JvmStatic
    fun stringToObject(data: String?): City {
        if (data == null)
            return City(null, null, null, null)
        val type = object : TypeToken<City>() {}.type

        return Gson().fromJson(data, type)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString(city: City?): String {
        return Gson().toJson(city)
    }

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
}
