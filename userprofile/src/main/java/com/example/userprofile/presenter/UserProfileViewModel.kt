package com.example.userprofile.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.userprofile.domin.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    private var userID = savedStateHandle.get<String>("userDetails")!!
    val getUserProfile = liveData {
        loader.postValue(true)
        emitSource(userProfileUseCase.getUserProfile(userID).onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}