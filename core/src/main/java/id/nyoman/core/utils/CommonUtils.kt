package id.nyoman.core.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.nyoman.core.data.ErrorResponse
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner, observer: Observer<T>) {
    removeObserver(observer)
    observe(owner, observer)
}

fun <T> Response<T>.getResult(
    onResult: (response: Response<T>) -> Unit,
    onFailure: (throwable: Throwable) -> Unit = { kotlin.run {} },
    onFinally: () -> Unit = { kotlin.run {} }
) {
    try {
        onResult.invoke(this)
    } catch (e: Exception) {
        onFailure.invoke(e)
    } finally {
        onFinally.invoke()
    }
}

fun ResponseBody.transformErrorResponse(): ErrorResponse {
    return Gson().fromJson(JSONObject(this.string()).toString())
}

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)