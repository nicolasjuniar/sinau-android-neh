package id.nyoman.core.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import id.nyoman.core.network.NetworkRepository

class GeneralDataSourceFactory<T>(
    private val networkRepository: NetworkRepository,
    private val viewModel: ViewModel,
    private val onLoadInitial: (
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, T>
    ) -> Unit,
    private val onLoadAfter: (
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, T>
    ) -> Unit,
    private val onLoadBefore: (params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, T>) -> Unit = { _, _ -> kotlin.run { } }
) : DataSource.Factory<Int, T>() {

    val movieDataSourceLiveData = MutableLiveData<GeneralDataSource<T>>()

    override fun create(): DataSource<Int, T> {
        val newsDataSource = GeneralDataSource<T>(
            networkRepository,
            viewModel,
            onLoadInitial,
            onLoadAfter,
            onLoadBefore
        )
        movieDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}