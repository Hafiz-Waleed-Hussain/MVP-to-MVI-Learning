package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser
import io.reactivex.Observable


data class MainViewState(val progress: Boolean = false, val error: Boolean = false, val data: List<GitHubUser> = listOf())


sealed class PartialViewState {
    object Progress : PartialViewState()
    object Error : PartialViewState()
    class Data(val data: List<GitHubUser>) : PartialViewState()
}

interface MainViewContract {

    fun buttonIntent(): Observable<Boolean>

    fun render(mainViewState: MainViewState)

}

