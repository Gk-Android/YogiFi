package com.example.yogifi.data.model.product

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ProductItem(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("offerPrice")
    val offerPrice: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("productDesc")
    val productDesc: String?,
    @SerializedName("productUrl")
    val productUrl: String?
) : Parcelable
