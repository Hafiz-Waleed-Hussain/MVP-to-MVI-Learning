package com.example.waleed.mvi.repositories.github;

import com.example.waleed.mvi.pojos.GitHubUser;
import com.example.waleed.mvi.pojos.GitHubUserRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by waleed on 22/07/2017.
 */
interface GitHubService {


    @GET("/users")
    Observable<List<GitHubUser>> getUsers();

    @GET("/users/{username}/repos")
    Observable<List<GitHubUserRepository>> getUserRepositories(@Path("username") String username);
}
