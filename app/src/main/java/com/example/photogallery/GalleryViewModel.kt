package com.example.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class GalleryViewModel(
    private val repository: GalleryRepository
) :ViewModel() {

    val galleryList = liveData {
        emitSource(repository.getGalleryList().asLiveData())
    }

}
