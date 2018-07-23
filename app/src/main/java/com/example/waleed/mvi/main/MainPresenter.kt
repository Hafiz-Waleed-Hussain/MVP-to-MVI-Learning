package com.example.waleed.mvi.main

import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainViewContract, private val repo: GitHubRepositoryDataSource) : MainActionContract {

    override fun loadData() {

        view.showProgress(true)
        view.showError(false)
        repo.getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view.showData(it)
                    view.showProgress(false)
                }, {
                    view.showError(true)
                    view.showProgress(false)
                })
    }


}