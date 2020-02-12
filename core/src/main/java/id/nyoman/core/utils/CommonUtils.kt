package id.nyoman.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bumptech.glide.Glide
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

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun ImageView.loadImage(url: String, context: Context) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun Context.showToast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
}

fun configPagedList(initialSize: Int, loadSize: Int, enablePlaceholder: Boolean = false) =
    PagedList.Config.Builder()
        .setPageSize(initialSize)
        .setInitialLoadSizeHint(loadSize)
        .setEnablePlaceholders(enablePlaceholder)
        .build()