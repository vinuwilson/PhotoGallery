package com.example.photogallery.userprofile.di

import com.example.photogallery.userprofile.network.UserProfileAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class UserProfileModule {

    @Provides
    fun getUserProfile(retrofit: Retrofit) : UserProfileAPI = retrofit.create(UserProfileAPI::class.java)

}