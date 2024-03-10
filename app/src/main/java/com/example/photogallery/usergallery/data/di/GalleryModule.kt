package com.example.photogallery.usergallery.data.di

import com.example.photogallery.usergallery.domin.GalleryRepositoryImp
import com.example.photogallery.usergallery.data.api.GalleryAPI
import com.example.photogallery.usergallery.data.api.GalleryRepository
import com.example.photogallery.usergallery.data.api.GalleryServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GalleryModule {

    @Provides
    @Singleton
    fun getRecentImageList(retrofit: Retrofit) : GalleryAPI = retrofit.create(GalleryAPI::class.java)

    @Provides
    @Singleton
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRepository(service: GalleryServices) : GalleryRepository = GalleryRepositoryImp(service)
}