package com.example.newsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.newsdemo.databinding.ActivityMainBinding
import com.example.newsdemo.repository.NewsLocalDataSource
import com.example.newsdemo.repository.NewsRemoteDataSource
import com.example.newsdemo.repository.NewsRepository
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    private val viewModel: MainViewModel = MainViewModel(
        NewsRepository(
            NewsRemoteDataSource(), NewsLocalDataSource(realm)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
            recyclerNews.apply {
                adapter = NewsAdapter()
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopHeadlines()
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}