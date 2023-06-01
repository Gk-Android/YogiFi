package com.example.yogifi.viewmodel.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogifi.data.model.product.ProductItem
import com.example.yogifi.data.model.product.ProductResponse
import com.example.yogifi.data.repository.ProductRepository
import com.example.yogifi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository):ViewModel() {
    private val productListLiveData : MutableLiveData<Resource<List<ProductItem>>> = MutableLiveData()
    val productList : LiveData<Resource<List<ProductItem>>>
        get() = productListLiveData

    fun getProductList() = viewModelScope.launch(Dispatchers.IO) {
        productListLiveData.postValue(Resource.Loading())
        val apiResult = repository.getProductList()
        productListLiveData.postValue(apiResult)
    }

}