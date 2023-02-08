package com.gosty.myotakulist.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gosty.myotakulist.core.data.source.local.entity.common.TitleEntity

class TitleConverter {
    @TypeConverter
    fun toList(value: String?): List<TitleEntity> {
        val listType = object : TypeToken<List<TitleEntity>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<TitleEntity?>): String = Gson().toJson(value)
}