package com.example.photogallery.usergallery.domin

import com.example.photogallery.usergallery.data.api.GalleryRepository
import com.example.photogallery.usergallery.data.model.RecentPhotos
import com.example.photogallery.usergallery.data.api.GalleryServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GalleryRepositoryImp @Inject constructor(
    private val service: GalleryServices
) : GalleryRepository {

    override suspend fun getGalleryList(): Flow<Result<RecentPhotos>> {
        return service.getGalleryList()
    }
}