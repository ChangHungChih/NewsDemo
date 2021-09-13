package com.example.newsdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdemo.model.Article
import com.example.newsdemo.repository.NewsRepository
import io.realm.RealmResults
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val articleList: LiveData<RealmResults<Article>> = newsRepository.getNewsCache().asLiveData()

    fun getTopHeadlines() {
        newsRepository.getTopHeadlines()
            .filterNotNull()
            .onEach {
                newsRepository.saveNewsCache(it.articles)
            }
            .launchIn(viewModelScope)
    }
}