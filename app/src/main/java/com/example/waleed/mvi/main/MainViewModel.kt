package com.example.waleed.mvi.main

import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val view: MainView, private val repo: GitHubRepositoryDataSource) : MainActionContract {




    override fun processIntent(mainViewIntent: MainViewIntent) {

    }


    override fun reduce(intent: MainViewAction): MainViewState =
            when(intent){
                MainViewAction.LoadData ->
            }


    override fun loadData(isSwipeRefresh: Boolean) {
        if (!isSwipeRefresh)
            view.showProgress(true)
        view.showError(false)
        repo.getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view.showData(it)
                    if (!isSwipeRefresh)
                        view.showProgress(false)
                    view.hideSwipeToRefresh()
                }, {
                    view.showError(true)
                    if (!isSwipeRefresh)
                        view.showProgress(false)
                    view.hideSwipeToRefresh()
                })
    }


}