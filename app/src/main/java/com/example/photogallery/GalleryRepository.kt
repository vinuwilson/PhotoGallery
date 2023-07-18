package com.example.photogallery

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryRepository {

    suspend fun getGalleryList() : Flow<Result<Photo>> {
        return flow {  }
    }

}
