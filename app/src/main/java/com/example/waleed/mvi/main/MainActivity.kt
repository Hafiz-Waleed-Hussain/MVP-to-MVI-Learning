package com.example.waleed.mvi.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.waleed.mvi.R
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.repositories.github.GitHubRepository
import com.example.waleed.mvi.repositories.github.GitHubServiceGenerator
import com.example.waleed.mvi.repositories.github.RemoteGitHubRepository
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewContract {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this,
                MainViewModelFactory(GitHubRepository.getInstance(RemoteGitHubRepository(
                        GitHubServiceGenerator.gitHubService("https://api.github.com")))))
                .get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.bind(this)
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.unbind()
    }

    override fun buttonIntent(): Observable<Boolean> = RxView.clicks(button).map { true }

    override fun render(mainViewState: MainViewState) {
        with(mainViewState) {
            showProgress(progress)
            showError(error)
            showData(data)
        }
    }


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


    private inline fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private inline fun gone(view: View) {
        view.visibility = View.GONE
    }


}
