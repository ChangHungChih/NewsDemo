package com.example.newsdemo

import com.example.newsdemo.http.newsApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiTest {

    @Test
    fun `test get news`() {
        runBlocking {
            newsApi.getTopHeadlines()
                .filterNotNull()
                .onEach {
                    println(it.status)
                    println(it.totalResults)
                    println(it.articles)
                }
                .collect()
        }
    }
}