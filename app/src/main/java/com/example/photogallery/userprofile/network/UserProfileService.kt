package com.example.photogallery.userprofile.network

import com.example.photogallery.userprofile.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserProfileService @Inject constructor(
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