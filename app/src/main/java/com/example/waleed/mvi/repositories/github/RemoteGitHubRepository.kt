package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.main.PartialViewState
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

internal class RemoteGitHubRepository(private val gitHubService: GitHubService) : GitHubRepositoryDataSource {

    override fun getUsers(): Observable<PartialViewState> = gitHubService.getUsers().map { PartialViewState.Data(it) as PartialViewState }
            .onErrorReturn { PartialViewState.Error }

    override fun getRepositories(username: String): Observable<List<GitHubUserRepository>> {
        return gitHubService.getUserRepositories(username)
    }
}
