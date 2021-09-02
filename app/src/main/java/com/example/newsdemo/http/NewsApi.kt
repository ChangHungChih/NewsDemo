package com.example.newsdemo.http

import com.example.newsdemo.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "c3a80e951ee8487c95cb46a764e4cd8a",
    ): Flow<NewsResponse>
}

val newsApi: NewsApi
    get() = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory())
        .build()
        .create(NewsApi::class.java)