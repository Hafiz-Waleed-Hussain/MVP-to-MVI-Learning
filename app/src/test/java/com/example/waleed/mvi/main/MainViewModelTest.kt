package com.example.waleed.mvi.main

import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Mock
    lateinit var gitHubRepositoryDataSource: GitHubRepositoryDataSource
    @Mock
    lateinit var viewContract: MainViewContract

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(gitHubRepositoryDataSource)
    }

    @Test
    fun `when loadData called verify show progress called`() {
        Mockito.`when`(viewContract.buttonIntent()).thenReturn(Observable.just(true))
        mainViewModel.bind(viewContract)

    }


}