package com.example.waleed.mvi.repositories.github

import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by waleed on 22/07/2017.
 */
interface GitHubService {


    @GET("/users")
    fun getUsers(): Observable<List<GitHubUser>>

    @GET("/users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Observable<List<GitHubUserRepository>>
}
