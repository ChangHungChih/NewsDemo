package com.example.newsdemo.model

import io.realm.RealmObject

open class Source : RealmObject() {
    open var id: String? = ""
    open var name: String? = ""
}
