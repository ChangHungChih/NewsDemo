package com.example.newsdemo.repository

import com.example.newsdemo.model.Article
import io.realm.Realm
import io.realm.RealmResults

class NewsLocalDataSource(val realm: Realm) {

    fun getNewsData(): RealmResults<Article> {
        return realm.where(Article::class.java).findAll()
    }

    fun saveNewsData(articleList: List<Article>) {
        with(realm) {
            beginTransaction()
            copyToRealmOrUpdate(articleList)
            commitTransaction()
        }
    }
}