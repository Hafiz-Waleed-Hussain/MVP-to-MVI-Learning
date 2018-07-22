package com.example.waleed.mvi.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.waleed.mvi.R
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.repositories.github.GitHubRepository
import com.example.waleed.mvi.repositories.github.GitHubServiceGenerator
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewContract {

    private lateinit var presenter: MainActionContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, GitHubRepository.getInstance(GitHubServiceGenerator.gitHubService("https://api.github.com")))

    }

    override fun render(viewState: MainViewState) {

        with(viewState) {
            showProgress(viewState.progress)
            showError(viewState.error)
            showData(viewState.data)
        }
    }

    override fun buttonClick(): Observable<Boolean> =
            RxView.clicks(button).map { true }


    private fun showProgress(boolean: Boolean) {
        if (boolean)
            visible(progressBar)
        else
            gone(progressBar)
    }


    private fun showError(boolean: Boolean) {
        if (boolean)
            visible(textView)
        else
            gone(textView)
    }


    private fun showData(listOf: List<GitHubUser>) {
        visible(recyclerView)
        recyclerView.adapter = MainAdapter(listOf)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun hideData() {
        gone(recyclerView)
    }


    private inline fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private inline fun gone(view: View) {
        view.visibility = View.GONE
    }


}
