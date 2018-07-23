package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.pojos.GitHubUser
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GitHubRepositoryTest {

    @Mock
    lateinit var remoteGitHubRepository: GitHubRepositoryDataSource

    private lateinit var gitHubRepository: GitHubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gitHubRepository = GitHubRepository.getInstance(remoteGitHubRepository)
    }


    @Test
    fun `when there is no user `() {
        Mockito.`when`(remoteGitHubRepository.getUsers()).thenReturn(Observable.fromArray(listOf(GitHubUser())))
        val test = gitHubRepository.getUsers().test()
        test.assertNoErrors()
        test.assertValue { it.size == 1 }
        test.assertComplete()
    }

    @Test
    fun `when there is no error `() {
        Mockito.`when`(remoteGitHubRepository.getUsers()).thenReturn(Observable.error(Exception("Network not available")))
        val test = gitHubRepository.getUsers().test()
        test.assertError{ it.message!! == "Network not available" }
        test.assertNotComplete()
    }
}