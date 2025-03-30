package com.example.assignment.data.api

import com.example.assignment.data.models.Product
import com.example.assignment.data.models.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("/products")
    suspend fun getProducts(
        @Query("limit") limit : Int,
        @Query("skip") skip : Int
    ):Response<Products>

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") id : Int
    ): Response<Product>

}