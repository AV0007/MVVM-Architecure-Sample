package com.example.moviesdemo.room

import android.graphics.ColorSpace
import androidx.room.TypeConverter
import com.example.moviesdemo.model.ModelDemo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.reflect.KClass


class ListConverters {

    @TypeConverter
    fun toTorrent(json: String): List<ModelDemo.Result> {
        val type = object : TypeToken<List<ModelDemo.Result>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(torrent: List<ModelDemo.Result>): String {
        val type = object: TypeToken<List<ModelDemo.Result>>() {}.type
        return Gson().toJson(torrent, type)
    }

}