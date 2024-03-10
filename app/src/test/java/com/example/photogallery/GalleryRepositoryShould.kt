package com.example.photogallery

import com.example.photogallery.usergallery.data.api.GalleryServices
import com.example.photogallery.usergallery.data.model.RecentPhotos
import com.example.photogallery.usergallery.domin.GalleryRepositoryImp
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

class GalleryRepositoryShould : BaseUnitTest() {

    private lateinit var repository: GalleryRepositoryImp
    private val service = mock<GalleryServices>()
    private val galleryList: RecentPhotos = mock()
    private val expected = Result.success(galleryList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchGalleryListFromService(): Unit = runBlocking {

        mockSuccessfulCase()

        repository.getGalleryList()

        verify(service, times(1)).getGalleryList()
    }

    @Test
    fun emitGalleryListFromService(): Unit = runBlocking {

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

        repository = GalleryRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getGalleryList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        repository = GalleryRepositoryImp(service)
    }
}