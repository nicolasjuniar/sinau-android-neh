package com.juniar.ancodev.sinauneh.feature.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.utils.GeneralRecyclerViewAdapter
import com.juniar.ancodev.sinauneh.utils.onChangeValue
import kotlinx.android.synthetic.main.fragment_post.rv_post
import kotlinx.android.synthetic.main.viewholder_post.view.tv_body
import kotlinx.android.synthetic.main.viewholder_post.view.tv_title
import org.koin.android.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private val postViewModel: PostViewModel by viewModel()

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
        postViewModel.liveDataPost.onChangeValue(this) {
            listPost.addAll(it)
            postAdapter.notifyDataSetChanged()
        }
    }
}