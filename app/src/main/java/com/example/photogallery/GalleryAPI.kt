package com.example.photogallery

import retrofit2.http.GET

const val FLICKR_API_KEY = "e9abde1807ca996f9f97ad37e48470be"

interface GalleryAPI {

//    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs&api_key=$FLICKR_API_KEY")
//    @GET("?method=flickr.photos.getRecent&api_key=e9abde1807ca996f9f97ad37e48470be&extras=owner_name%2Cdescription%2C+date_taken%2C+tags%2C+url_m%2C&format=json&nojsoncallback=1")
//    @GET("?method=flickr.photos.getRecent&api_key=$FLICKR_API_KEY&extras=owner_name,description,+date_taken,+tags,+url_m,&format=json&nojsoncallback=1")
//    suspend fun getGalleryList() : Photos

    @GET(
        "?method=flickr.photos.getRecent&api_key=$FLICKR_API_KEY" +
                "&extras=owner_name,description,+date_taken,+tags,+url_m,+url_s" +
                "&format=json&nojsoncallback=1"
    )
    suspend fun getGalleryList(): PhotosRecentResponse
}