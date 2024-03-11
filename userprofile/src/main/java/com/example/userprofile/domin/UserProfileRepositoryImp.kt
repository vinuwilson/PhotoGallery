package com.example.userprofile.domin

import com.example.userprofile.data.api.UserProfileRepository
import com.example.userprofile.data.api.UserProfileService
import com.example.userprofile.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileRepositoryImp @Inject constructor(
    private val service: UserProfileService
) : UserProfileRepository {

    override suspend fun getUserProfile(userId: String): Flow<Result<UserProfile>> {
        return service.getUserProfile(userId)
    }
}