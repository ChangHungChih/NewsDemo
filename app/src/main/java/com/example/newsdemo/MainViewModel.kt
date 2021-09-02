package com.example.newsdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdemo.http.newsApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel : ViewModel() {
    private var _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>> = _articleList

    fun getTopHeadlines() {
        newsApi.getTopHeadlines()
            .filterNotNull()
            .onEach {
                _articleList.value = it.articles
            }
            .launchIn(viewModelScope)
    }
}