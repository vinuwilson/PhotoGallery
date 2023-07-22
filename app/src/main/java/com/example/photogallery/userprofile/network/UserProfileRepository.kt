package com.example.photogallery.userprofile.network

import com.example.photogallery.userprofile.model.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
    private val service: UserProfileService
) {

    suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>> {
        return service.getUserProfile(userId)
    }
}