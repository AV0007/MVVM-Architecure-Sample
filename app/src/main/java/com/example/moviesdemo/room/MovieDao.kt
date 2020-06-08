package com.example.moviesdemo.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesdemo.model.ModelDemo

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Enitity)

    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<Enitity>>



}
