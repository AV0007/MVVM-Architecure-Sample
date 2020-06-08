package com.example.moviesdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesdemo.R
import com.example.moviesdemo.model.ModelDemo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    val model: ModelDemo.Result?
        get() = intent.getParcelableExtra("Model")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        iv_image.transitionName="abc"
        tv_title.text = getString(R.string.title) + " " + ":" +" "+ model?.title
        tv_vote_avg.text = getString(R.string.voting_avg) + " " + ":" +" "+ model?.vote_average.toString()
        tv_overview.text = getString(R.string.overview) + " " + ":" +" "+ model?.overview
        tv_release_date.text = getString(R.string.release_date) + " " + ":" +" "+ model?.release_date

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342" + model?.poster_path)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(iv_image)

    }
}
