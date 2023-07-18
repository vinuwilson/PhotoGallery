package com.example.photogallery

import retrofit2.http.GET

interface GalleryAPI {

    @GET("")
    suspend fun getGalleryList() : Photos

}
