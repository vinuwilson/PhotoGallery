package com.example.photogallery.usergallery.network

import com.example.photogallery.usergallery.model.RecentPhotos
import retrofit2.http.GET

const val FLICKR_API_KEY = "e9abde1807ca996f9f97ad37e48470be"

interface GalleryAPI {

    @GET(
        "?method=flickr.photos.getRecent&api_key=$FLICKR_API_KEY" +
                "&extras=owner_name,description,+date_taken,+tags,+url_m,+url_s,+icon_server" +
                "&format=json&nojsoncallback=1"
    )
    suspend fun getGalleryList(): RecentPhotos
}