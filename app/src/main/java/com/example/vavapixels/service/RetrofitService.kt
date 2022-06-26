package com.example.vavapixels.service

import com.example.vavapixels.models.Pixel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("Viper")
    fun getAllPixelViper(): Call<List<Pixel>>

    companion object{
        private val retrofitService:RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(retrofitService::class.java)
        }

        fun getInstance () : RetrofitService{
            return retrofitService
        }
    }
}