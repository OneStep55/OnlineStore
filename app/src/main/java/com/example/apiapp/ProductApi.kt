package com.example.apiapp

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("/api/products")
    suspend fun getProducts(): Response<ProductResponse>
}