package com.example.userprofile.domin

import com.example.userprofile.data.api.UserProfileRepository
import com.example.userprofile.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend fun getUserProfile(userID: String): Flow<Result<UserProfile>> {
        return repository.getUserProfile(userID).onEach {
            if (it.isSuccess)
                it.getOrNull()!!.photos.photo.map {
                    it.image =
                        "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                }
        }
    }
}