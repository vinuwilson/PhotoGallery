package com.example.photogallery.userprofile.data.api

import com.example.photogallery.BuildConfig
import com.example.photogallery.userprofile.data.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Query

interface UserProfileAPI {

    @GET(
        "?method=flickr.people.getPublicPhotos&api_key=${BuildConfig.FLICKR_API_KEY}" +
                "&safe_search=1" +
                "&format=json&nojsoncallback=1"
    )
    suspend fun getUserProfile(@Query("user_id") userId: String): UserProfile

}


