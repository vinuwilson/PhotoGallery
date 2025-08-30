package com.example.photogallery.usergallery.domin

import com.example.photogallery.usergallery.data.model.RecentPhotos
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    suspend fun getGalleryList() : Flow<Result<RecentPhotos>>
}
