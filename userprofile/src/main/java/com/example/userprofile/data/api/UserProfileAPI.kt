package com.example.userprofile.data.api

import com.example.userprofile.data.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Query

const val FLICKR_API_KEY = "e9abde1807ca996f9f97ad37e48470be"

interface UserProfileAPI {

    @GET(
        "?method=flickr.people.getPublicPhotos&api_key=${FLICKR_API_KEY}" +
                "&safe_search=1" +
                "&format=json&nojsoncallback=1"
    )
    suspend fun getUserProfile(@Query("user_id") userId: String): UserProfile

}


