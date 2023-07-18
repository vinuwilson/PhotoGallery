package com.example.photogallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GalleryServicesShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var services: GalleryServices
    private val api : GalleryAPI = mock()
    private val galleryList = mock<Photos>()
    private val expected = Result.success(galleryList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchGalleryListFromAPI(): Unit = runBlocking {

        mockSuccessfulCase()

        services.getGalleryList().first()

        verify(api, times(1)).getGalleryList()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem(): Unit = runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, services.getGalleryList().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() : Unit = runBlocking {

        mockFailureCase()

        assertEquals(exception.message, services.getGalleryList().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getGalleryList()).thenReturn(galleryList)

        services = GalleryServices(api)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getGalleryList()).thenThrow(RuntimeException("Damn backend error"))

        services = GalleryServices(api)
    }

}