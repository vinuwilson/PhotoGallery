package com.example.photogallery

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GalleryServices(
    private val api: GalleryAPI
) {

    suspend fun getGalleryList(): Flow<Result<Photos>> {
        return flow {
            emit(Result.success(api.getGalleryList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
