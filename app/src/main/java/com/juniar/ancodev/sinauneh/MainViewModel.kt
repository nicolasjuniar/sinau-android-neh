package com.juniar.ancodev.sinauneh

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.network.NetworkRepository
import com.juniar.ancodev.sinauneh.utils.getResult
import com.juniar.ancodev.sinauneh.utils.transformErrorResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    val liveDataPost = MutableLiveData<ArrayList<PostModel>>()

    init {
        getAllPost()
//        login("nico@gmail.com", "123456")
    }

    fun getAllPost() = viewModelScope.launch {
        networkRepository.getAllPosts().getResult({
            it.body().let {
                liveDataPost.postValue(it)
            }
        }, {
            Timber.d("error", it.message.orEmpty())
        })
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        networkRepository.login(email, password).getResult({
            if (it.isSuccessful) {
                it.body()?.let {
                    Log.d("suskses", it.toString())
                }
            } else {
                it.errorBody()?.let {
                    Log.d("error toh", it.transformErrorResponse().toString())
                }
            }
        }, {
            Log.d("error", it.message.orEmpty())
        })
    }
}