package com.example.waleed.mvi.repositories.github;

import com.example.waleed.mvi.pojos.GitHubUser;
import com.example.waleed.mvi.pojos.GitHubUserRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by waleed on 22/07/2017.
 */

interface GitHubRepositoryDataSource {

    Observable<List<GitHubUser>> getUsers();

    Observable<List<GitHubUserRepository>> getRepositories(String usename);
}
