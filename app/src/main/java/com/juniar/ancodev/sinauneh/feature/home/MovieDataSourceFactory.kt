package com.juniar.ancodev.sinauneh.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import id.nyoman.core.data.MovieModel
import id.nyoman.core.network.NetworkRepository

class MovieDataSourceFactory(
    private val networkRepository: NetworkRepository,
    private val viewModel: HomeViewModel
) : DataSource.Factory<Int, MovieModel>() {

    val movieDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieModel> {
        val newsDataSource = MovieDataSource(networkRepository, viewModel)
        movieDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}