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

sealed class MainViewAction {

    object LoadData : MainViewAction()
    object SwipeToRefresh : MainViewAction()

}


interface MainView {

    fun render(state: MainViewState)

    fun intents(): Observable<MainViewIntent>

}

interface MainActionContract {

    fun processIntent(mainViewIntent: MainViewIntent)

    fun reduce(mainViewAction: MainViewAction): MainViewState

}