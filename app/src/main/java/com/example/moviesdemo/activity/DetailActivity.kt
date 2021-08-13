package com.example.moviesdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.moviesdemo.R
import com.example.moviesdemo.databinding.ActivityDetailBinding
import com.example.moviesdemo.model.ModelDemo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    val model: ModelDemo.Result?
        get() = intent.getParcelableExtra("Model")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.setMovie(model)




    }
}
