package com.example.photogallery.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}