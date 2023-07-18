package com.example.photogallery

import kotlinx.coroutines.flow.Flow

class GalleryRepository(
    private val service: GalleryServices
) {

    suspend fun getGalleryList() : Flow<Result<Photos>> {
        return service.getGalleryList()
    }

}
