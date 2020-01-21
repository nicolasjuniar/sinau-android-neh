package com.juniar.ancodev.sinauneh.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.juniar.ancodev.sinauneh.utils.reObserve

abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewReady(savedInstanceState)
    }

    abstract fun onViewReady(savedInstanceState: Bundle?)

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseActivity, Observer { data -> data?.let(action) })
    }
}