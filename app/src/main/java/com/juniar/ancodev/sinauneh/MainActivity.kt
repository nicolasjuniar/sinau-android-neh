package com.juniar.ancodev.sinauneh

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.base.BaseActivity
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.utils.GeneralRecyclerViewAdapter
import com.juniar.ancodev.sinauneh.utils.onChangeValue
import kotlinx.android.synthetic.main.activity_main.rv_post
import kotlinx.android.synthetic.main.viewholder_post.view.tv_body
import kotlinx.android.synthetic.main.viewholder_post.view.tv_title
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {


    private val mainViewModel: MainViewModel by viewModel()

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

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        mainViewModel.liveDataPost.onChangeValue(this) {
            listPost.addAll(it)
            postAdapter.notifyDataSetChanged()
        }
    }
}
