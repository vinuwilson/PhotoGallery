package com.example.photogallery.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserProfileViewModelFactory(
    private val repository: UserProfileRepository,
    private val userId: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repository, userId) as T
    }
}