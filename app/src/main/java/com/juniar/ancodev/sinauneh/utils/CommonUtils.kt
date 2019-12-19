package com.juniar.ancodev.sinauneh.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<T>.onChangeValue(lifeCycleOwner: LifecycleOwner, onPostValue: (value: T) -> Unit) {
    this.observe(lifeCycleOwner, Observer<T> { value ->
        value?.let {
            onPostValue.invoke(it)
        }
    })
}