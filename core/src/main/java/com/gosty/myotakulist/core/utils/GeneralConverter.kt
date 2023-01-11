package com.gosty.myotakulist.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gosty.myotakulist.core.data.source.local.entity.common.GeneralEntity

class GeneralConverter {
    @TypeConverter
    fun toList(value: String?): List<GeneralEntity> {
        val listType = object : TypeToken<List<GeneralEntity>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<GeneralEntity?>): String = Gson().toJson(value)
}