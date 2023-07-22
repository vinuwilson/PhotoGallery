package com.example.photogallery.usergallery.di

import com.example.photogallery.usergallery.network.GalleryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class GalleryModule {

    @Provides
    fun getRecentImageList(retrofit: Retrofit) : GalleryAPI = retrofit.create(GalleryAPI::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}