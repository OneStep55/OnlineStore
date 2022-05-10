package com.example.apiapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Product(
    val __v: Int,
    val _id: String,
    val category: String,
    val checked: Boolean,
    val content: String,
    val createdAt: String,
    val description: String,
    val images: Images,
    val price: Int,
    val product_id: String,
    val sold: Int,
    val title: String,
    val updatedAt: String
): Serializable