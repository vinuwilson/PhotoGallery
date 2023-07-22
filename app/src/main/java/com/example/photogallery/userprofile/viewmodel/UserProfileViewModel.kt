package com.example.photogallery.userprofile.viewmodel

import androidx.lifecycle.*
import com.example.photogallery.userprofile.model.UserProfile
import com.example.photogallery.userprofile.network.UserProfileRepository
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val userProfile = MutableLiveData<Result<UserProfile>> ()

    fun getUserProfile(userID :String) {
        viewModelScope.launch {
            loader.postValue(true)
            repository.getUserProfile(userID).onEach {
                loader.postValue(false)
            }
                .collect { it ->
                    if (it.isSuccess)
                        it.getOrNull()!!.photos.photo.map {
                            it.image =
                                "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                        }
                    userProfile.postValue(it)
                }
        }
    }
}