package com.example.photogallery.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach

class UserProfileViewModel(
    private val repository: UserProfileRepository,
    private val userId: String
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val userProfile = liveData {
        loader.postValue(true)
        emitSource(repository.getUserProfile(userId).onEach { it ->
            if (it.isSuccess)
                it.getOrNull()!!.photos.photo.map {
                    it.image =
                        "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                }
            loader.postValue(false)

        }.asLiveData())
    }
}