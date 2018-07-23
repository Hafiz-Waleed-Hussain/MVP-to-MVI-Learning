package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

class GitHubRepository private constructor(gitHubService: GitHubService) : GitHubRepositoryDataSource {

    private val remoteGitHubRepository: GitHubRepositoryDataSource

    init {
        remoteGitHubRepository = RemoteGitHubRepository(gitHubService)
    }


    override fun getUsers(): Observable<List<GitHubUser>> {
        return remoteGitHubRepository.getUsers()
    }

    override fun getRepositories(username: String): Observable<List<GitHubUserRepository>> {
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
