package com.example.yogifi.data.webservice

import com.example.yogifi.data.model.product.ProductResponse
import com.example.yogifi.data.model.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface Webservice {
    @GET("bc09a745-4346-4025-9611-9da76366dbbc")
    suspend fun getProductList():Response<ProductResponse>

    @GET("aaf97364-eedc-46a5-8f9e-56eb4b3cedd2")
    suspend fun getProfileData() : Response<ProfileResponse>
}