package com.example.waleed.mvi.repositories.github

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by waleed on 22/07/2017.
 */

object GitHubServiceGenerator {

    private var gitHubService: GitHubService? = null

    fun gitHubService(baseUrl: String): GitHubService {

        if (gitHubService == null) {

            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.i("Retrofit Network", message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES).build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            gitHubService = retrofit.create(GitHubService::class.java)
        }
        return gitHubService!!
    }

}
