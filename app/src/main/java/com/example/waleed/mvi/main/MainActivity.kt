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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewContract {

    private lateinit var presenter: MainActionContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, GitHubRepository.getInstance(RemoteGitHubRepository(GitHubServiceGenerator.gitHubService("https://api.github.com"))))
        button.setOnClickListener { presenter.loadData() }
        swipeToRefresh.setOnRefreshListener {
            presenter.loadData(true)
        }
    }


    override fun showProgress(boolean: Boolean) {
        if (boolean)
            visible(progressBar)
        else
            gone(progressBar)
    }

    override fun showError(boolean: Boolean) {
        if (boolean)
            visible(textView)
        else
            gone(textView)
    }

    override fun showData(listOf: List<GitHubUser>) {
        visible(recyclerView)
        recyclerView.adapter = MainAdapter(listOf)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun hideSwipeToRefresh() {
        swipeToRefresh.isRefreshing = false
    }



    private inline fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private inline fun gone(view: View) {
        view.visibility = View.GONE
    }


}
