package com.example.newsdemo.repository

import com.example.newsdemo.http.newsApi
import com.example.newsdemo.model.NewsResponse
import kotlinx.coroutines.flow.Flow

class NewsRemoteDataSource {

    fun getTopHeadlines(): Flow<NewsResponse> {
        return newsApi.getTopHeadlines()
    }
}