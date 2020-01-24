package com.juniar.ancodev.sinauneh.feature.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.nyoman.core.data.UserModel
import id.nyoman.core.network.NetworkRepository
import id.nyoman.core.utils.getResult
import kotlinx.coroutines.launch

class ProfileViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    val liveDataUser = MutableLiveData<UserModel>()

    fun getUsers(id: Int) = viewModelScope.launch {
        networkRepository.getUsers(id).getResult({
            it.body()?.let {
                liveDataUser.postValue(it)
            }
        }, {
            Log.d("error", it.message.orEmpty())
        })
    }
}