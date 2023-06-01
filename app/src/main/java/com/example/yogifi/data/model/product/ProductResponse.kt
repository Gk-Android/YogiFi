package com.example.yogifi.data.model.product

import com.example.yogifi.data.model.product.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val data : Product
)
