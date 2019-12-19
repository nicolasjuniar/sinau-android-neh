package com.juniar.ancodev.sinauneh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.network.NetworkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    val liveDataPost = MutableLiveData<ArrayList<PostModel>>()

    init {
        getAllPost()
    }

    fun getAllPost() = viewModelScope.launch {
        try {
            liveDataPost.postValue(networkRepository.getAllPosts())
        } catch (e: Exception) {
            Timber.d("error", e.message.orEmpty())
        }
    }
}