package com.example.yogifi.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yogifi.data.repository.ProductRepository

class ProductViewModelFactory(private val repository: ProductRepository ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repository) as T
    }
}