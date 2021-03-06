package id.nyoman.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import id.nyoman.core.utils.reObserve

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        reObserve(this@BaseFragment, Observer { data -> data?.let(action) })
    }
}