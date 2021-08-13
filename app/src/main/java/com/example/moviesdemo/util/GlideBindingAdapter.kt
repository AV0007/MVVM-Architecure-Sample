package com.example.moviesdemo.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesdemo.R

class GlideBindingAdapter {
}
@BindingAdapter("imageurl")
fun setImage(view: ImageView, url: String) {

    val context = view.context
    val ro = RequestOptions()
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
    view.transitionName="abc"
    Glide.with(context).setDefaultRequestOptions(ro)
        .load("https://image.tmdb.org/t/p/w342"+(url))
        .into(view)

}