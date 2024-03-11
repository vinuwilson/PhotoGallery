package com.example.userprofile.data.api

import com.example.userprofile.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {

    suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>>
}