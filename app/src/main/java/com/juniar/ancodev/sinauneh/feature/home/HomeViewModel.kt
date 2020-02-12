package com.juniar.ancodev.sinauneh.feature.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.nyoman.core.State
import id.nyoman.core.data.MovieModel
import id.nyoman.core.network.NetworkRepository
import id.nyoman.core.utils.*
import kotlinx.coroutines.launch

class HomeViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var listMovie: LiveData<PagedList<MovieModel>>

    var stateLiveData = MutableLiveData<State>()

    private val movieDataSourceFactory: GeneralDataSourceFactory<MovieModel> =
        GeneralDataSourceFactory({ _, callback ->
            updateState(State.LOADING)
            viewModelScope.launch {
                networkRepository.getNowPlaying().getResult({
                    updateState(State.DONE)
                    it.body()?.let { response ->
                        callback.onResult(response.results, null, 2)
                    }
                }, {
                    updateState(State.ERROR)
                    callback.onError(it)
                })
            }
        }, { params, callback ->
            updateState(State.LOADING)
            viewModelScope.launch {
                networkRepository.getNowPlaying().getResult({
                    updateState(State.DONE)
                    it.body()?.let { response ->
                        callback.onResult(response.results, params.key + 1)
                    }
                }, {
                    updateState(State.ERROR)
                    callback.onError(it)
                })
            }
        })

    private val testActive = SingleLiveEvent<Boolean>()

    private val isFirst = MutableLiveData<Boolean>()

    init {
        listMovie = LivePagedListBuilder(movieDataSourceFactory, configPagedList(10, 10)).build()
    }

    fun listIsEmpty(): Boolean {
        return listMovie.value?.isEmpty() ?: true
    }

    fun observetestActive(): SingleLiveEvent<Boolean> = testActive

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

    private fun updateState(state: State) {
        stateLiveData.postValue(state)
    }
}