package com.example.yogifi.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yogifi.data.model.profile.ProfileResponse
import com.example.yogifi.data.repository.ProfileRepository
import com.example.yogifi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val profileLiveData: MutableLiveData<Resource<ProfileResponse>> = MutableLiveData()
    val profileData : LiveData<Resource<ProfileResponse>>
        get() = profileLiveData

    fun getProfileData() = viewModelScope.launch(Dispatchers.IO) {
        profileLiveData.postValue(Resource.Loading())
        val response = repository.getProfileData()
        profileLiveData.postValue(response)
    }
}