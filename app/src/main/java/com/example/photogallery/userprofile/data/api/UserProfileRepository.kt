package com.example.photogallery.userprofile.data.api

import com.example.photogallery.userprofile.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {

    suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>>
}