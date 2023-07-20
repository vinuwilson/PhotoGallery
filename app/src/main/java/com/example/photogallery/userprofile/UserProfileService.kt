package com.example.photogallery.userprofile

import com.example.photogallery.userprofile.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserProfileService(
    private val api: UserProfileAPI
) {

    suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>> {
        return flow {
            emit(Result.success(api.getUserProfile(userId)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}