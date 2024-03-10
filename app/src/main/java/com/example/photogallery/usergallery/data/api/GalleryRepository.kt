package com.example.photogallery.usergallery.data.api

import com.example.photogallery.usergallery.data.model.RecentPhotos
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    suspend fun getGalleryList() : Flow<Result<RecentPhotos>>
}
