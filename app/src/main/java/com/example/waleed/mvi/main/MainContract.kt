package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser
import io.reactivex.Observable


data class MainViewState(val progress: Boolean = false, val error: Boolean = false, val data: List<GitHubUser> = listOf())

sealed class PartialViewState {

    object ProgressState : PartialViewState()

    object ErrorState : PartialViewState()

    class FetchedData(val data: List<GitHubUser>) : PartialViewState()
}

interface MainViewContract {

    fun render(viewState: MainViewState)

    fun buttonClick(): Observable<Boolean>
//    fun showProgress()
//    fun hideProgress()
//    fun showError()
//    fun hideError()
//    fun showData(listOf: MutableList<GitHubUser>)
//    fun hideData()

}

interface MainActionContract {


}