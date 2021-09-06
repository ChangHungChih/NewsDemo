package com.example.newsdemo.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Article : RealmObject() {
    open var author: String? = ""
    open var content: String? = ""
    open var description: String? = ""
    open var publishedAt: String? = ""
    open var source: Source? = null
    open var title: String? = ""

    @PrimaryKey
    open var url: String? = ""
    open var urlToImage: String? = ""
}
