package com.example.newsdemo.repository

import com.example.newsdemo.model.Article
import com.example.newsdemo.model.NewsResponse
import io.realm.RealmResults
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val remote: NewsRemoteDataSource,
    private val local: NewsLocalDataSource
) {

    fun getTopHeadlines(): Flow<NewsResponse> {
        return remote.getTopHeadlines()
    }

    fun getNewsCache(): RealmResults<Article> {
        return local.getNewsData()
    }

    fun saveNewsCache(articleList: List<Article>) {
        local.saveNewsData(articleList)
    }
}