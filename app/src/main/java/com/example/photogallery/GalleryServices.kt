package com.example.photogallery

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GalleryServices {

    suspend fun getGalleryList() : Flow<Result<Photos>> {
       return flow {  }
    }

}
