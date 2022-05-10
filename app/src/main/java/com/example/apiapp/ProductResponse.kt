package com.example.apiapp

data class ProductResponse(
    val products: List<Product>,
    val result: Int,
    val status: String
)