package com.example.yogifi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yogifi.data.model.product.ProductItem
import com.example.yogifi.data.model.product.ProductResponse
import com.example.yogifi.data.webservice.Webservice
import com.example.yogifi.utils.Resource

class ProductRepository(private val webservice: Webservice) {

    suspend fun getProductList(): Resource<List<ProductItem>> {
        val response = webservice.getProductList()

        return if (response.isSuccessful) {
            val productList = response.body()?.data?.products
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

