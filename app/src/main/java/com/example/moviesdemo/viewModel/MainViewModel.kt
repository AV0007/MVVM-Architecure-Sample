package com.example.moviesdemo.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesdemo.repository.MainRepository
import com.example.moviesdemo.room.Enitity
import com.example.moviesdemo.util.RetrofitUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: MainRepository
    var mAllUsers: LiveData<List<Enitity>>
    var error = MutableLiveData<Throwable>()
    val onSuccess = MutableLiveData<Enitity>()


    @SuppressLint("CheckResult")
    fun getData() {
        RetrofitUtil.apiService().getData(
            api_key = "8041131ee6ef1bced95f31970c7d3d03"
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onSuccess(it) }, { onFailure(it) })

    }

    private fun onFailure(it: Throwable?) {
        error.postValue(
            it
        )
    }

    private fun onSuccess(it: Enitity?) {
        onSuccess.postValue(it)

    }

    fun insert(md: Enitity?) {
        userRepository.insert(md)
    }




    init {
        userRepository = MainRepository(application)
        mAllUsers = userRepository.getAllUsers()

    }
}