package com.example.photogallery.usergallery.data.api

import com.example.photogallery.usergallery.data.model.RecentPhotos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GalleryServices @Inject constructor(
    private val api: GalleryAPI
) {

    suspend fun getGalleryList(): Flow<Result<RecentPhotos>> {
        return flow {
            emit(Result.success(api.getGalleryList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
