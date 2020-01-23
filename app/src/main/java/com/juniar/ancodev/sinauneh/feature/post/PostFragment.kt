package com.juniar.ancodev.sinauneh.feature.post

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.data.PostModel
import id.nyoman.core.base.BaseFragment
import id.nyoman.core.utils.DiffCallback
import id.nyoman.core.utils.GeneralRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.viewholder_post.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val postViewModel by viewModel<PostViewModel>()

    private val listPost = arrayListOf<PostModel>()

    private val diffCallback: DiffCallback by inject()

    private val postAdapter by lazy {
        GeneralRecyclerViewAdapter(
            diffCallback,
            R.layout.viewholder_post, listPost,
            { post, _, _ ->
                postViewModel.changeTestActive()
                Toast.makeText(context, post.title, Toast.LENGTH_SHORT).show()
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
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
        postViewModel.liveDataPost.onChangeValue {
//            listPost.addAll(it)
            postAdapter.setData(it)
        }

        postViewModel.observeIsFirst().value?.let {
            if (!it) postViewModel.getAllPost()
        }

        postViewModel.observetestActive().onChangeValue {
            Log.d("logd", "cekActive Changed: $it")
        }

        postViewModel.observeIsFirst().onChangeValue {
            Log.d("logd", "observeIsFirst: $it")
        }
    }
}