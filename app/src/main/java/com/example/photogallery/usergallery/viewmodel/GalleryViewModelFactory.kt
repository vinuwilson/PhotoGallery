package com.example.photogallery.usergallery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photogallery.usergallery.network.GalleryRepository
import javax.inject.Inject

class GalleryViewModelFactory @Inject constructor(
    private val repository: GalleryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}
