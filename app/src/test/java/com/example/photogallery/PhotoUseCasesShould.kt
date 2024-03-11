package com.example.photogallery

import com.example.photogallery.usergallery.data.api.GalleryRepository
import com.example.photogallery.usergallery.data.model.RecentPhotos
import com.example.photogallery.usergallery.domin.PhotoUseCases
import com.example.photogallery.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PhotoUseCasesShould : BaseUnitTest() {

    private lateinit var photoUseCases: PhotoUseCases
    private val repository: GalleryRepository = mock()
    private val galleryList: RecentPhotos = mock()
    private val expected = Result.success(galleryList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchPhotosFromRepository(): Unit = runBlocking {

        mockSuccessfulCase()

        photoUseCases.getPhotos()

        verify(repository, times(1)).getGalleryList()
    }

    @Test
    fun emitGalleryListFromRepository() = runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, photoUseCases.getPhotos().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlocking {

        mockFailureCase()

        assertEquals(exception, photoUseCases.getPhotos().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase() {

        whenever(repository.getGalleryList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        photoUseCases = PhotoUseCases(repository)
    }

    private suspend fun mockFailureCase() {

        whenever(repository.getGalleryList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        photoUseCases = PhotoUseCases(repository)
    }
}