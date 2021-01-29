package com.surkhojb.daggerhiltsample.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListStringTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<String?>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<String>?>() {}.getType()
        return gson.toJson(list, type)
    }

    @TypeConverter //
    fun fromStringToList(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val gson = Gson()
        val type: Type =
            object : TypeToken<List<String>?>() {}.getType()
        return gson.fromJson(value, type)
    }
}