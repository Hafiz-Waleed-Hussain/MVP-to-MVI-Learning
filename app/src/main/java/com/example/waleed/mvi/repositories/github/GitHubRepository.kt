package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.main.PartialViewState
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Created by waleed on 22/07/2017.
 */

class GitHubRepository private constructor(gitHubService: GitHubService) {

    private val remoteGitHubRepository: GitHubRepositoryDataSource

    init {
        remoteGitHubRepository = RemoteGitHubRepository(gitHubService)
    }


    fun getUsers(): Observable<PartialViewState> {
        return remoteGitHubRepository.users.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn { listOf() }
                .map { PartialViewState.FetchedData(it) }


    }

    fun getRepositories(username: String): Observable<List<GitHubUserRepository>> {
        return remoteGitHubRepository.getRepositories(username)
    }

    companion object {

        private var INSTANCE: GitHubRepository? = null


        fun getInstance(gitHubService: GitHubService): GitHubRepository {
            if (INSTANCE == null)
                INSTANCE = GitHubRepository(gitHubService)
            return INSTANCE as GitHubRepository
        }
    }
}
