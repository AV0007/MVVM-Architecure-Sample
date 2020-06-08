package com.example.moviesdemo.util

import com.example.moviesdemo.model.ModelDemo
import com.example.moviesdemo.room.Enitity
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiServices {

    //8041131ee6ef1bced95f31970c7d3d03
    @GET("movie/popular")
    fun getData(
        @Query("api_key") api_key: String
    ): Observable<Enitity>


}