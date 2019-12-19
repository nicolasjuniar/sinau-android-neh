package com.juniar.ancodev.sinauneh

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.base.BaseActivity
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.network.NetworkService
import com.juniar.ancodev.sinauneh.utils.GeneralRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.rv_post
import kotlinx.android.synthetic.main.viewholder_post.view.tv_body
import kotlinx.android.synthetic.main.viewholder_post.view.tv_title
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val networkService: NetworkService by inject()

    private val liveDataPost = MutableLiveData<ArrayList<PostModel>>()

    private val listPost = arrayListOf<PostModel>()

    private val postAdapter by lazy {
        GeneralRecyclerViewAdapter(
            R.layout.viewholder_post, listPost,
            { _, _, _ ->
            },
            { post, view ->
                with(view) {
                    tv_title.text = post.title
                    tv_body.text = post.body
                }
            })
    }

    private val postObserver = Observer<ArrayList<PostModel>> { value ->
        value?.let {
            listPost.addAll(it)
            postAdapter.notifyDataSetChanged()
        }
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        GlobalScope.launch {
            try {
                liveDataPost.postValue(networkService.getAllPosts())
            } catch (e: Exception) {
                Timber.d("error", e.message.orEmpty())
            }
        }
        liveDataPost.observe(this, postObserver)
    }
}
