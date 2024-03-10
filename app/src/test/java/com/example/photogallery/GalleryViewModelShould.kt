package com.example.photogallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.photogallery.usergallery.data.model.RecentPhotos
import com.example.photogallery.usergallery.domin.PhotoUseCases
import com.example.photogallery.usergallery.presenter.GalleryViewModel
import com.example.photogallery.utils.BaseUnitTest
import com.example.photogallery.utils.MainCoroutineScopeRule
import com.example.photogallery.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GalleryViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: GalleryViewModel
    private val photoUseCases : PhotoUseCases = mock()
    private val galleryList: RecentPhotos = mock()
    private val expected = Result.success(galleryList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchGalleryListFromServer(): Unit = runBlocking {

        mockSuccessfulCase()

        viewModel.galleryList.getValueForTest()

        verify(photoUseCases, times(1)).getPhotos()
    }

    @Test
    fun emitGalleryListFromServer() = runBlocking {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.galleryList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(): Unit = runBlocking {

        mockFailureCase()

        assertEquals(exception, viewModel.galleryList.getValueForTest()!!.exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase() {
        whenever(photoUseCases.getPhotos()).thenReturn(
            flow {
                emit(expected)
            }
        )

        viewModel = GalleryViewModel(photoUseCases)
    }

    private suspend fun mockFailureCase() {
        whenever(photoUseCases.getPhotos()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        viewModel = GalleryViewModel(photoUseCases)
    }
}