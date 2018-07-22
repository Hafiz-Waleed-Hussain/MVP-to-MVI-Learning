package com.example.waleed.mvi.main

import com.example.waleed.mvi.pojos.GitHubUser

interface MainViewContract{
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
    fun showData(listOf: MutableList<GitHubUser>)
    fun hideData()

}

interface MainActionContract{

    fun loadData()

}