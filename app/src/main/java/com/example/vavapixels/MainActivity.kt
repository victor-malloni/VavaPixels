package com.example.vavapixels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vavapixels.adapters.MainAdapter
import com.example.vavapixels.databinding.ActivityMainBinding
import com.example.vavapixels.models.Pixel
import com.example.vavapixels.repositories.MainRepository
import com.example.vavapixels.service.RetrofitService
import com.example.vavapixels.viewmodel.main.MainViewModel
import com.example.vavapixels.viewmodel.main.MainViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    lateinit var viewModel:MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.pixelList.observe(this, Observer {pixels ->
            Log.i("nome", "onStart")
            adapter.setPixelList(pixels)
        })

        viewModel.errorMessage.observe(this, Observer {message->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllPixelViper()
    }

}