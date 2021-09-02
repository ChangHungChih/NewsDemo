package com.example.newsdemo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object MainBinding {

    @BindingAdapter("articleList")
    @JvmStatic
    fun bindNews(recyclerView: RecyclerView, articleList: List<Article>?) {
        if (articleList == null) {
            return
        }
        (recyclerView.adapter as NewsAdapter).submitList(articleList)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String?) {
        url?.apply {
            Glide.with(imageView.context).load(this).into(imageView)
        }
    }
}