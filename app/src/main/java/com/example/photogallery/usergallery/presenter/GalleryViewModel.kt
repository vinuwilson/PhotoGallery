package com.example.photogallery.usergallery.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.photogallery.usergallery.domin.PhotoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val photoUseCases: PhotoUseCases
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val galleryList = liveData {
        loader.postValue(true)
        emitSource(photoUseCases.getPhotos().onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}
