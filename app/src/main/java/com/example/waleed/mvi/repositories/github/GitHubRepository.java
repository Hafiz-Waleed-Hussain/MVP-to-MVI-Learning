package com.example.waleed.mvi.repositories.github;

import com.example.waleed.mvi.pojos.GitHubUser;
import com.example.waleed.mvi.pojos.GitHubUserRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by waleed on 22/07/2017.
 */

public class GitHubRepository implements GitHubRepositoryDataSource {

    private final GitHubRepositoryDataSource remoteGitHubRepository;

    private static GitHubRepository INSTANCE;


    public static GitHubRepository getInstance(GitHubService gitHubService) {
        if (INSTANCE == null)
            INSTANCE = new GitHubRepository(gitHubService);
        return INSTANCE;
    }

    private GitHubRepository(GitHubService gitHubService) {
        remoteGitHubRepository = new RemoteGitHubRepository(gitHubService);
    }


    @Override
    public Observable<List<GitHubUser>> getUsers() {
        return remoteGitHubRepository.getUsers();
    }

    @Override
    public Observable<List<GitHubUserRepository>> getRepositories(String username) {
        return remoteGitHubRepository.getRepositories(username);
    }
}
