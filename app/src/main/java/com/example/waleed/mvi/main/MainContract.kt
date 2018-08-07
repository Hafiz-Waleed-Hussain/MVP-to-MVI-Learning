package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser
import io.reactivex.Observable


data class MainViewState(val hideSwipeToRefresh: Boolean = false,
                         val progress: Boolean = false,
                         val error: Boolean = false,
                         val data: List<GitHubUser> = listOf())


sealed class MainViewIntent {
    object LoadData : MainViewIntent()
    object SwipeToRefresh : MainViewIntent()
}

sealed class MainAction {
    object Error : MainAction()
    object Progress : MainAction()

}


interface MainView {

    fun render(state: MainViewState)

    fun intents(): Observable<MainViewIntent>

}

interface MainActionContract {

    fun processIntent(intents: Observable<MainViewIntent>)

    fun reduce(mainViewAction: MainAction): MainViewState

}