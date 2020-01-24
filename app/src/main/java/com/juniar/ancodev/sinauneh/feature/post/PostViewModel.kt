package com.juniar.ancodev.sinauneh.feature.post

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.nyoman.core.data.PostModel
import id.nyoman.core.network.NetworkRepository
import id.nyoman.core.utils.SingleLiveEvent
import id.nyoman.core.utils.getResult
import id.nyoman.core.utils.transformErrorResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class PostViewModel(private val networkRepository: NetworkRepository) : ViewModel() {


    val liveDataPost = MutableLiveData<List<PostModel>>()

    private val testActive = SingleLiveEvent<Boolean>()

    private val isFirst = MutableLiveData<Boolean>()

    var state: Parcelable? = null

    init {
        testActive.value = false
        isFirst.value = false
//        login("nico@gmail.com", "123456")
    }

    fun getAllPost() = viewModelScope.launch {
        networkRepository.getAllPosts().getResult({
            it.body()?.let { list ->
                testActive.value = list.isNotEmpty()
                isFirst.value = list.isNotEmpty()
                liveDataPost.postValue(list)
            }
        }, {
            Timber.d("error", it.message.orEmpty())
        })
    }
    fun observetestActive() : SingleLiveEvent<Boolean> = testActive

    fun observeIsFirst(): LiveData<Boolean> = isFirst

    fun changeTestActive() {
        testActive.value?.let { testActive.postValue(!it) }
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