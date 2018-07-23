package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser

interface MainViewContract {
    fun showProgress(b: Boolean)
    fun showError(b: Boolean)
    fun showData(listOf: List<GitHubUser>)
}

interface MainActionContract {

    fun loadData()

}