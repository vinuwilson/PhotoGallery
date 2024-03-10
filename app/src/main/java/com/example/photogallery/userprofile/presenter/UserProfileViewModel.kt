package com.example.photogallery.userprofile.presenter

import androidx.lifecycle.*
import com.example.photogallery.usergallery.data.model.Photo
import com.example.photogallery.userprofile.domin.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    private var userID = savedStateHandle.get<Photo>("userDetails")!!.owner
    val getUserProfile = liveData {
        loader.postValue(true)
        emitSource(userProfileUseCase.getUserProfile(userID).onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}