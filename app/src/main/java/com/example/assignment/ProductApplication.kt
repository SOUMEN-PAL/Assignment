package com.example.assignment

import android.app.Application
import com.example.assignment.data.api.ProductsService
import com.example.assignment.data.api.RetrofitHelper
import com.example.assignment.domain.ProductRepository

class ProductApplication : Application() {
    lateinit var repository: ProductRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    fun initialize(){
        val apiService = RetrofitHelper.getInstance().create(ProductsService::class.java)
        repository = ProductRepository(apiService)
    }

}