package com.example.waleed.mvi.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.waleed.mvi.R
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.repositories.github.GitHubRepository
import com.example.waleed.mvi.repositories.github.GitHubServiceGenerator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewContract {

    private lateinit var presenter: MainActionContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, GitHubRepository.getInstance(GitHubServiceGenerator.gitHubService("https://api.github.com")))
        button.setOnClickListener { presenter.loadData() }
    }


    override fun showProgress() {
        visible(progressBar)
    }

    override fun hideProgress() {
        gone(progressBar)
    }

    override fun showError() {
        visible(textView)
    }

    override fun hideError() {
        gone(textView)
    }

    override fun showData(listOf: MutableList<GitHubUser>) {
        visible(recyclerView)
        recyclerView.adapter = MainAdapter(listOf)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun hideData() {
        gone(recyclerView)
    }


    private inline fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private inline fun gone(view: View) {
        view.visibility = View.GONE
    }


}
