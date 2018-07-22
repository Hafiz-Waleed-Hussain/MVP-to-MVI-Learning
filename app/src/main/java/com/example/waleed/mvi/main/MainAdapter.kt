package com.example.waleed.mvi.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waleed.mvi.R
import com.example.waleed.mvi.pojos.GitHubUser
import com.example.waleed.mvi.pojos.GitHubUserRepository
import com.example.waleed.mvi.repositories.github.GitHubRepository
import kotlinx.android.synthetic.main.row.view.*

class MainAdapter(private val list: List<GitHubUser>) : RecyclerView.Adapter<MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
            MainViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int) = R.layout.row

}


class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(gitHubUserRepository: GitHubUser) {
        view.textView2.text = gitHubUserRepository.login
    }

}