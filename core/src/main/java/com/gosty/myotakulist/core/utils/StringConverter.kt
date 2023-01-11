package com.gosty.myotakulist.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringConverter {
    @TypeConverter
    fun toList(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<String?>): String = Gson().toJson(value)
}