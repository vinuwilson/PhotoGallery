package com.example.photogallery.usergallery.data.api

import com.example.photogallery.usergallery.data.model.RecentPhotos
import com.example.photogallery.usergallery.domin.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GalleryRepositoryImp @Inject constructor(
    private val service: GalleryServices
) : GalleryRepository {

    override suspend fun getGalleryList(): Flow<Result<RecentPhotos>> {
        return service.getGalleryList()
    }
}