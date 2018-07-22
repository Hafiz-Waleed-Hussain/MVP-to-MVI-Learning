package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUserRepository
import com.example.waleed.mvi.repositories.github.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainViewContract, private val repo: GitHubRepository) : MainActionContract {

    override fun loadData() {

        view.showProgress()
        view.hideData()
        view.hideError()
        repo.users.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view.showData(it)
                    view.hideProgress()
                }, {
                    view.showError()
                    view.hideProgress()
                })
    }


}