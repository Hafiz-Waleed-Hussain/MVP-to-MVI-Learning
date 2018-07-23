package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser
import io.reactivex.Observable


data class MainViewState(val progress: Boolean = false,
                         val error: Boolean = false,
                         val data: List<GitHubUser> = listOf())

sealed class PartialViewState {

    object Progress : PartialViewState()

    object Error : PartialViewState()

    class FetchedData(val data: List<GitHubUser>) : PartialViewState()
}


interface MainViewContract {

    fun buttonClick() : Observable<Boolean>

    fun render(state: MainViewState)

}

interface MainActionContract {

    fun bind(view:MainViewContract)

    fun unbind()

    fun reduce(partialViewState: PartialViewState): MainViewState
}