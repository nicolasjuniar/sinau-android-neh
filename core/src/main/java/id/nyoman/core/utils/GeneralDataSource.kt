package id.nyoman.core.utils

import androidx.paging.PageKeyedDataSource

class GeneralDataSource<T>(
    private val onLoadInitial: (
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) -> Unit,
    private val onLoadAfter: (
        params: LoadParams<Int>,
        callback: LoadCallback<Int, T>
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