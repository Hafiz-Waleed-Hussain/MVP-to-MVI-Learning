package com.example.waleed.mvi.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.waleed.mvi.repositories.github.GitHubRepositoryDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainViewModelFactory(val repo: GitHubRepositoryDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw IllegalStateException("wrong view model")
    }
}

class MainViewModel(private val repo: GitHubRepositoryDataSource) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val stateSubject = BehaviorSubject.create<PartialViewState>()

    fun bind(view: MainViewContract) {

        val buttonIntent = view.buttonIntent()
                .flatMap {
                    repo.getUsers()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .startWith(PartialViewState.Progress)
                }

        val merge = Observable.merge(listOf(buttonIntent)).subscribeWith(stateSubject)
        compositeDisposable.add(merge.scan(MainViewState(), { t1, t2 -> reduce(t1, t2) }).subscribe { view.render(it) })

    }

    fun unbind() {
        compositeDisposable.clear()
    }

    fun reduce(previousState: MainViewState, partialViewState: PartialViewState): MainViewState = when (partialViewState) {
        PartialViewState.Progress -> MainViewState(progress = true)
        PartialViewState.Error -> MainViewState(error = true)
        is PartialViewState.Data -> MainViewState(data = partialViewState.data)
    }


}