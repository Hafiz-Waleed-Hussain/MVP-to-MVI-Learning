package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable

/**
 * Created by waleed on 22/07/2017.
 */

interface GitHubRepositoryDataSource {

    fun getUsers(): Observable<List<GitHubUser>>

    fun getRepositories(usename: String): Observable<List<GitHubUserRepository>>
}
