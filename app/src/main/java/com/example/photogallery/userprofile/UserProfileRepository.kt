package com.example.photogallery.userprofile

import com.example.photogallery.userprofile.model.UserProfile
import kotlinx.coroutines.flow.Flow

class UserProfileRepository(
    private val service: UserProfileService
) {

    suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>> {
        return service.getUserProfile(userId)
    }
}