package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    lateinit var repo: GitHubRepositoryDataSource

    @Mock
    lateinit var view: MainViewContract

    private lateinit var presenter: MainActionContract

    @Before
    fun setUp() {

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, repo)
        Mockito.`when`(repo.getUsers()).thenReturn(Observable.empty())
    }

    @Test
    fun `when loadData called verify show progress called`() {
        presenter.loadData()
        Mockito.verify(view).showProgress(true)
    }

    @Test
    fun `when loadData called verify show error called`() {
        presenter.loadData()
        Mockito.verify(view).showError(false)
    }

    @Test
    fun `when loadData success verify show data called`() {
        val data = listOf(GitHubUser())
        Mockito.`when`(repo.getUsers()).thenReturn(Observable.fromArray(data))
        presenter.loadData()
        Mockito.verify(view).showData(data)
    }

    @Test
    fun `when loadData success verify show progress called`() {
        val data = listOf(GitHubUser())
        Mockito.`when`(repo.getUsers()).thenReturn(Observable.fromArray(data))
        presenter.loadData()
        Mockito.verify(view).showProgress(false)
    }

    @Test
    fun `when loadData error verify show error called`() {
        Mockito.`when`(repo.getUsers()).thenReturn(Observable.error(Exception()))
        presenter.loadData()
        Mockito.verify(view).showError(true)
    }

    @Test
    fun `when loadData error verify show progress called`() {
        val argumentCaptor = ArgumentCaptor.forClass(Boolean::class.java)
        Mockito.`when`(repo.getUsers()).thenReturn(Observable.error(Exception()))
        presenter.loadData()
        Mockito.verify(view, Mockito.times(2)).showProgress(argumentCaptor.capture())
        Assert.assertEquals(false, argumentCaptor.value)
    }


}