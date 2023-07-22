package com.example.photogallery.usergallery.network

import com.example.photogallery.usergallery.model.RecentPhotos
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val service: GalleryServices
) {

    suspend fun getGalleryList() : Flow<Result<RecentPhotos>> {
        return service.getGalleryList()
    }

}
