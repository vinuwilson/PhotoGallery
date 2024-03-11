package com.example.userprofile.data.di

import com.example.userprofile.data.api.UserProfileAPI
import com.example.userprofile.data.api.UserProfileRepository
import com.example.userprofile.data.api.UserProfileService
import com.example.userprofile.domin.UserProfileRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserProfileModule {

    @Provides
    @Singleton
    fun getUserProfile(retrofit: Retrofit): UserProfileAPI =
        retrofit.create(UserProfileAPI::class.java)

    @Provides
    @Singleton
    fun getUserProfileRepository(service: UserProfileService): UserProfileRepository =
        UserProfileRepositoryImp(service)

}