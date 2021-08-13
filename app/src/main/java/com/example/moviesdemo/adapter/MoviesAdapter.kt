package com.example.moviesdemo.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat

import com.example.moviesdemo.R
import com.example.moviesdemo.activity.DetailActivity
import com.example.moviesdemo.activity.MainActivity
import com.example.moviesdemo.model.ModelDemo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movies.view.*


class MoviesAdapter(private val context: Context, val list: ArrayList<ModelDemo.Result>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       Log.e("OnCreateViewHolder","OnCreateViewHolderCalled")

        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        Log.e("getItemCountCalled","getItemCountCalled")

        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.e("OnBindViewHolderCalled",position.toString()+"  "+"OnBindViewHolderCalled")
        holder.bindView(position)

    }

    inner class MyViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        fun bindView(position: Int) {

Log.e("MYViewHolderCalled","MYViewHolderCalled")
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w342" + list[position].poster_path)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.movie_image)

            itemView.movie_name.text = list[position].title


            itemView.ll_layout.setOnClickListener {


                val intent = Intent((context as MainActivity), DetailActivity::class.java)
                intent.putExtra("Model", list[position])
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (context),
                        (itemView.movie_image as View?)!!,
                        "abc"
                    )
              context.startActivity(intent, options.toBundle())




            }


        }
    }

}
