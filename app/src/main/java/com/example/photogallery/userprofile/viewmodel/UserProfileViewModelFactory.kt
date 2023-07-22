package com.example.photogallery.userprofile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photogallery.userprofile.network.UserProfileRepository
import javax.inject.Inject

class UserProfileViewModelFactory @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repository) as T
    }
}