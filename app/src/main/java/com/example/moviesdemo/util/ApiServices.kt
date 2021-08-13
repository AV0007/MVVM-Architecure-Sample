package com.example.moviesdemo.util

import com.example.moviesdemo.LoginRequest
import com.example.moviesdemo.StreamingRequest
import com.example.moviesdemo.response.LoginResponse
import com.example.moviesdemo.response.StreamingResponse
import com.example.moviesdemo.room.Enitity
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServices {

    @GET("movie/popular")
    fun getData(
        @Query("api_key") api_key: String
    ): Observable<Enitity>

   // @FormUrlEncoded

    @POST("device/connect")
    fun connect(
        @Header("developerkey") developerkey: String,
        @Header("token") token: String,
        @Body streamingRequest: StreamingRequest
    ): Observable<StreamingResponse>


    @POST("user/login")
    fun login(
        @Header("developerkey") developerkey: String,
        @Body loginRequest: LoginRequest
    ): Observable<LoginResponse>

}