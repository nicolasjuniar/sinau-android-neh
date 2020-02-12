package com.juniar.ancodev.sinauneh.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import id.nyoman.core.State
import id.nyoman.core.data.MovieModel
import id.nyoman.core.network.NetworkRepository
import id.nyoman.core.utils.getResult
import kotlinx.coroutines.launch

class MovieDataSource(
    private val networkRepository: NetworkRepository,
    private val viewModel: HomeViewModel
) :
    PageKeyedDataSource<Int, MovieModel>() {

    var state = MutableLiveData<State>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {
        updateState(State.LOADING)
        viewModel.viewModelScope.launch {
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
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        updateState(State.LOADING)
        viewModel.viewModelScope.launch {
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
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

}