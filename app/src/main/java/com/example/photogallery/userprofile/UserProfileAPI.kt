package com.example.photogallery.userprofile

import com.example.photogallery.BuildConfig
import com.example.photogallery.userprofile.model.UserProfile
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


