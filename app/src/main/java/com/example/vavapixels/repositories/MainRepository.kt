package com.example.vavapixels.repositories

import com.example.vavapixels.service.RetrofitService

//repositorio da "'Main' activity"
class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllPixelViper()=retrofitService.getAllPixelViper()


}