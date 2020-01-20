package com.juniar.ancodev.sinauneh.feature.post

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.base.BaseFragment
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.utils.GeneralRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_post.rv_post
import kotlinx.android.synthetic.main.viewholder_post.view.tv_body
import kotlinx.android.synthetic.main.viewholder_post.view.tv_title
import org.koin.android.viewmodel.ext.android.viewModel

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val postViewModel by viewModel<PostViewModel>()

    private val listPost = arrayListOf<PostModel>()

    private val postAdapter by lazy {
        GeneralRecyclerViewAdapter(
            R.layout.viewholder_post, listPost,
            { post, _, _ ->
                findNavController().navigate(
                    R.id.action_navigation_post_to_detailPostActivity,
                    bundleOf("title" to post.title, "body" to post.body)
                )
            },
            { post, view ->
                with(view) {
                    tv_title.text = post.title
                    tv_body.text = post.body
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postViewModel.liveDataPost.onChangeValue {
            listPost.addAll(it)
            postAdapter.notifyDataSetChanged()
        }
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}