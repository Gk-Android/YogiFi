package com.example.yogifi.data.model.product


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Product(
    @SerializedName("products")
    val products: List<ProductItem>
)