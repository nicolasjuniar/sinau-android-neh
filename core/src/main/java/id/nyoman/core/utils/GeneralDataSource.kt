package id.nyoman.core.utils

import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import id.nyoman.core.network.NetworkRepository

class GeneralDataSource<T>(
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
    private val onLoadBefore: (params: LoadParams<Int>, callback: LoadCallback<Int, T>) -> Unit
) :
    PageKeyedDataSource<Int, T>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        onLoadInitial.invoke(params, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        onLoadAfter.invoke(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        onLoadBefore.invoke(params, callback)
    }

}