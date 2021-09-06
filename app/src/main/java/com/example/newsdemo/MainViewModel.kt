package com.example.newsdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdemo.http.newsApi
import com.example.newsdemo.model.Article
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel : ViewModel() {
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    val articleLLL : LiveData<RealmResults<Article>> = realm.where(Article::class.java).findAll().asLiveData()

    fun getTopHeadlines() {
        newsApi.getTopHeadlines()
            .filterNotNull()
            .onEach {
                Realm.getDefaultInstance().apply {
                    beginTransaction()
                    copyToRealmOrUpdate(it.articles)
                    commitTransaction()
                    close()
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }
}