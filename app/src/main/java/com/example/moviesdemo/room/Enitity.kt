package com.example.moviesdemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesdemo.model.ModelDemo

@Entity(tableName = "movie")
data class Enitity(
    @PrimaryKey(autoGenerate = true)
    var mid:Int?=null,
    @ColumnInfo var page:Int=0,
    @ColumnInfo var results:List<ModelDemo.Result>?=null,
    @ColumnInfo var total_pages:Int=0,
    @ColumnInfo var total_results:Int=0



)
