package com.juniar.ancodev.sinauneh.feature.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import com.juniar.ancodev.sinauneh.data.PostModel
import com.juniar.ancodev.sinauneh.utils.DiffCallback
import com.juniar.ancodev.sinauneh.utils.GeneralRecyclerViewAdapter
import com.juniar.ancodev.sinauneh.utils.onChangeValue
import kotlinx.android.synthetic.main.fragment_post.rv_post
import kotlinx.android.synthetic.main.viewholder_post.view.tv_body
import kotlinx.android.synthetic.main.viewholder_post.view.tv_title
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private val postViewModel: PostViewModel by viewModel()

    private val listPost = arrayListOf<PostModel>()

    private val diffCallback: DiffCallback by inject()

    private val postAdapter by lazy {
        GeneralRecyclerViewAdapter(
            diffCallback,
            R.layout.viewholder_post, listPost,
            { model, pos, _ ->
                postViewModel.changeTestActive()
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
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
//            listPost.addAll(it)
            postAdapter.setData(it)
        }

        postViewModel.observeIsFirst().value?.let {
            if (!it) postViewModel.getAllPost()
        }

        postViewModel.observetestActive().onChangeValue(this) {
            Log.d("logd", "cekActive Changed: $it")
        }

        postViewModel.observeIsFirst().onChangeValue(this) {
            Log.d("logd", "observeIsFirst: $it")
        }
    }
}