package id.nyoman.core.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class GeneralDataSourceFactory<T>(
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

    val generalDataSourceLiveData = MutableLiveData<GeneralDataSource<T>>()

    override fun create(): DataSource<Int, T> {
        val newsDataSource = GeneralDataSource(
            onLoadInitial,
            onLoadAfter,
            onLoadBefore
        )
        generalDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}