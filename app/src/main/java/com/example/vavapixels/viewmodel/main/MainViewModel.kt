package com.example.vavapixels.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vavapixels.models.Pixel
import com.example.vavapixels.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val pixelList = MutableLiveData<List<Pixel>>()
    val errorMessage = MutableLiveData<String>()


    //pegar resposta da chamada
    fun getAllPixelViper() {
        val requisicao = repository.getAllPixelViper()
        requisicao.enqueue(object : Callback<List<Pixel>> {
            override fun onResponse(call: Call<List<Pixel>>, response: Response<List<Pixel>>) {
                pixelList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Pixel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })

    }

}