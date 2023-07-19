package com.example.photogallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GalleryRepositoryShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: GalleryRepository
    private val service = mock<GalleryServices>()
    private val galleryList : PhotosRecentResponse = mock()
    private val expected = Result.success(galleryList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchGalleryListFromService(): Unit = runBlocking {

        mockSuccessfulCase()

        repository.getGalleryList()

        verify(service, times(1)).getGalleryList()
    }

    @Test
    fun emitGalleryListFromService(): Unit =  runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, repository.getGalleryList().first())
    }

    @Test
    fun emitError(): Unit = runBlocking {

        mockFailureCase()

        assertEquals(exception, repository.getGalleryList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getGalleryList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        repository = GalleryRepository(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getGalleryList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        repository = GalleryRepository(service)
    }
}