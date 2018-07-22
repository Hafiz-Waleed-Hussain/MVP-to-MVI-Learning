package com.example.waleed.mvi.repositories.github;

import com.example.waleed.mvi.main.PartialViewState;
import com.example.waleed.mvi.pojos.GitHubUser;
import com.example.waleed.mvi.pojos.GitHubUserRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by waleed on 22/07/2017.
 */

class RemoteGitHubRepository implements GitHubRepositoryDataSource {

    private final GitHubService gitHubService;

    public RemoteGitHubRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }


    @Override
    public Observable<List<GitHubUser>> getUsers() {
        return gitHubService.getUsers();
    }


    @Override
    public Observable<List<GitHubUserRepository>> getRepositories(String username) {
        return gitHubService.getUserRepositories(username);
    }
}
