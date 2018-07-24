package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.main.PartialViewState
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

class GitHubRepository private constructor(val remoteGitHubRepository: GitHubRepositoryDataSource) : GitHubRepositoryDataSource {


    override fun getUsers(): Observable<PartialViewState> {
        return remoteGitHubRepository.getUsers()
    }

    override fun getRepositories(username: String): Observable<List<GitHubUserRepository>> {
        return remoteGitHubRepository.getRepositories(username)
    }

    companion object {

        private var INSTANCE: GitHubRepository? = null


        fun getInstance(remoteGitHubRepository: GitHubRepositoryDataSource): GitHubRepository {
//            if(remoteGitHubRepository !is RemoteGitHubRepository){
//                throw  RuntimeException("Please add RemoteGitHubRepository.class ")
//            }
            if (INSTANCE == null)
                INSTANCE = GitHubRepository(remoteGitHubRepository)
            return INSTANCE as GitHubRepository
        }
    }
}
