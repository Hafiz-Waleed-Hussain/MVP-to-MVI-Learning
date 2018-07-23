package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

internal class RemoteGitHubRepository(private val gitHubService: GitHubService) : GitHubRepositoryDataSource {

    override fun getUsers(): Observable<List<GitHubUser>> = gitHubService.getUsers()

    override fun getRepositories(username: String): Observable<List<GitHubUserRepository>> {
        return gitHubService.getUserRepositories(username)
    }
}
