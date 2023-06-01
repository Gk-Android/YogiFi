package com.example.yogifi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yogifi.data.model.profile.ProfileResponse
import com.example.yogifi.data.webservice.Webservice
import com.example.yogifi.utils.Resource

class ProfileRepository(private val webservice: Webservice) {
    suspend fun getProfileData() : Resource<ProfileResponse> {
        val response = webservice.getProfileData()

        return if (response.isSuccessful) {
            val productList = response.body()
            if (productList != null) {
                Resource.Success(productList)
            } else {
                Resource.Error("Empty response body")
            }
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            Resource.Error(errorMessage)
        }
    }
}