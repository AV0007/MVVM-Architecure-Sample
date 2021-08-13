package com.example.moviesdemo.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdemo.R
import com.example.moviesdemo.adapter.MoviesAdapter
import com.example.moviesdemo.model.ModelDemo
import com.example.moviesdemo.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var wordViewModel: MainViewModel
    private var mNetworkReceiver: BroadcastReceiver? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        init()
        initControl()
        setReceiver()
    }

    private fun setReceiver() {
        mNetworkReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                try {
                    if (isOnline()) {
                        updateUi(false)
                        wordViewModel.getData()
                    }
                    else{

                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

            }

        }
        registerNetworkBroadcast()

    }

    private fun initControl() {
        btn_retry.visibility = View.GONE
        btn_retry.setOnClickListener {
            wordViewModel.getData()
        }
    }

    fun init() {

        wordViewModel.onSuccess.observe(this, Observer {
            updateUi(true)
            wordViewModel.insert(it)
        })

        wordViewModel.error.observe(this, Observer {
            updateUi(true)
            btn_retry.visibility = View.VISIBLE

        })




        wordViewModel.mAllUsers.observe(this, Observer {

            if ((it as ArrayList).size > 0) {
                updateUi(true)
                tv_no_data.visibility = View.GONE
                rv_movies.visibility=View.VISIBLE

                setAdapter(it[0].results as ArrayList)

            } else {
                tv_no_data.visibility = View.VISIBLE
                rv_movies.visibility=View.GONE
            }

        })
    }

    fun setAdapter(arrayList: java.util.ArrayList<ModelDemo.Result>) {
        rv_movies.adapter = MoviesAdapter(this, arrayList)
        rv_movies.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?


    }

    fun updateUi(isEnable: Boolean) {
        if (isEnable){
            shimmer.stopShimmerAnimation()
        }
        else{
            shimmer.startShimmerAnimation()
        }
    }

    private fun isOnline(): Boolean {
        return try {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetworkReceiver)
    }

    private fun registerNetworkBroadcast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(
                mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(
                mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }


}
