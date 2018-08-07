package com.example.waleed.mvi.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.waleed.mvi.R
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.repositories.github.GitHubRepository
import com.example.waleed.mvi.repositories.github.GitHubServiceGenerator
import com.example.waleed.mvi.repositories.github.RemoteGitHubRepository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainActionContract

    private lateinit var subject: PublishSubject<MainViewIntent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subject = PublishSubject.create()
        setContentView(R.layout.activity_main)
        presenter = MainViewModel(this, GitHubRepository.getInstance(RemoteGitHubRepository(GitHubServiceGenerator.gitHubService("https://api.github.com"))))
        button.setOnClickListener { subject.onNext(MainViewIntent.LoadData) }
        swipeToRefresh.setOnRefreshListener {
            subject.onNext(MainViewIntent.SwipeToRefresh)
        }
    }

    override fun render(state: MainViewState) {
        showError(state.error)
        showProgress(state.progress)
        showData(state.data)
        if (state.hideSwipeToRefresh)
            hideSwipeToRefresh()
    }

    override fun intents(): Observable<MainViewIntent> = subject


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

    private fun hideSwipeToRefresh() {
        swipeToRefresh.isRefreshing = false
    }


    private inline fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private inline fun gone(view: View) {
        view.visibility = View.GONE
    }


}
