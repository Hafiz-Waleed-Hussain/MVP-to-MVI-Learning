package com.example.waleed.mvi.main

import com.example.waleed.mvi.repositories.github.GitHubRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val repo: GitHubRepository) : MainActionContract {

    private val mainViewState: MainViewState = MainViewState()
    private val compositeDisposable = CompositeDisposable()
    override fun bind(view: MainViewContract) {
        val buttonObservable = view.buttonClick().flatMap {
            view.render(reduce(PartialViewState.Progress))
            repo.getUsers()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
        }

        val mergeIntents = Observable.merge(listOf(buttonObservable))
        compositeDisposable.add(mergeIntents.subscribe({
            view.render(reduce(PartialViewState.FetchedData(it)))
        }, {
            view.render(reduce(PartialViewState.Error))
        }))
    }

    override fun unbind() {
        compositeDisposable.clear()
    }

    override fun reduce(partialViewState: PartialViewState) = when (partialViewState) {
        is PartialViewState.Progress -> mainViewState.copy(progress = true)
        is PartialViewState.Error -> mainViewState.copy(error = true)
        is PartialViewState.FetchedData -> mainViewState.copy(data = partialViewState.data)
    }


}