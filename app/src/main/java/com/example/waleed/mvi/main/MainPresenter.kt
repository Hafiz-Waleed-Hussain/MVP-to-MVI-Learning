package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUserRepository
import com.example.waleed.mvi.repositories.github.GitHubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainViewContract, private val repo: GitHubRepository) : MainActionContract {

    private val mainState = MainViewState()

    init {
        val observable = view.buttonClick().flatMap {
            repo.getUsers().startWith { PartialViewState.ProgressState }
        }

                observable.subscribe { view.render(reduce(it)) }
//        view.buttonClick().flatMap {
//            repo.getUsers().doOnSubscribe {
//                view.render(reduce(PartialViewState.ProgressState))
//            }.onErrorReturn {
//
//            }
//        }
//                .subscribe({
//                    view.render(reduce(PartialViewState.FetchedData(it)))
//                }, {
//                    view.render(reduce(PartialViewState.ErrorState))
//                })
    }


    private fun reduce(partialViewState: PartialViewState): MainViewState {
        return when (partialViewState) {
            PartialViewState.ProgressState -> mainState.copy(progress = true)
            PartialViewState.ErrorState -> mainState.copy(error = true)
            is PartialViewState.FetchedData -> mainState.copy(data = partialViewState.data)
        }
    }


}