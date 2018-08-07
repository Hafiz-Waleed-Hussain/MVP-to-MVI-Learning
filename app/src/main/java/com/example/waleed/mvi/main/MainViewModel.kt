package com.example.waleed.mvi.main

import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val view: MainView, private val repo: GitHubRepositoryDataSource) : MainActionContract {

    override fun processIntent(intents: Observable<MainViewIntent>) {
        intents.subscribe({ this::actionFromIntent })
    }


    private fun actionFromIntent(mainViewIntent: MainViewIntent) {
        when (mainViewIntent) {
            MainViewIntent.LoadData -> MainAction.DownloadData
            MainViewIntent.SwipeToRefresh -> MainAction.SwipeToRefresh
        }
    }

    override fun reduce(mainViewAction: MainAction): MainViewState {
        when(mainViewAction){

        }
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