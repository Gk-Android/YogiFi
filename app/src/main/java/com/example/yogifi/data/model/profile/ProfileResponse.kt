package com.example.yogifi.data.model.profile


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProfileResponse(
    @SerializedName("address")
    val address: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("pointsEarned")
    val pointsEarned: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("walletBalance")
    val walletBalance: String?
)