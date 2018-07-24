package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.main.PartialViewState
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

interface GitHubRepositoryDataSource {

    fun getUsers(): Observable<PartialViewState>

    fun getRepositories(usename: String): Observable<List<GitHubUserRepository>>
}
