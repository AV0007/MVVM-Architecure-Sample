package com.example.moviesdemo.util


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.concurrent.TimeUnit
//https://api.themoviedb.org/3/movie/popular?api_key=8041131ee6ef1bced95f31970c7d3d03
//https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
object RetrofitUtil {
    fun apiService(
            baseUrl: String = "https://api.themoviedb.org/3/",
            connectionTimeOutInSec: Long = 15,
            readTimeOutInSec: Long = 90,
            writeTimeOutInSec: Long = 240

    ): ApiServices {
        return getRetrofitClient(getokhttpClientBuilder(
                connectionTimeOutInSec,
                readTimeOutInSec,
                writeTimeOutInSec
        ), baseUrl).create(ApiServices::class.java)
    }

    fun getRetrofitClient(okHttpClientBuilder: OkHttpClient.Builder, baseUrl: String) = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClientBuilder.build())//
            .baseUrl(baseUrl)
            .build()

    fun getokhttpClientBuilder(connectTimeoutInSec: Long, readTimeoutInSec: Long, writeTimeoutInSec: Long): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }
}
