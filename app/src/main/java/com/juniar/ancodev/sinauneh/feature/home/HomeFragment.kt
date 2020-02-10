package com.juniar.ancodev.sinauneh.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import id.nyoman.core.BuildConfig
import id.nyoman.core.base.BaseFragment
import id.nyoman.core.data.MovieModel
import id.nyoman.core.utils.DiffCallback
import id.nyoman.core.utils.GeneralRecyclerViewAdapter
import id.nyoman.core.utils.loadImage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.viewholder_movie.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel by viewModel<HomeViewModel>()

    private val listMovie = arrayListOf<MovieModel>()

    private val diffCallback: DiffCallback by inject()

    private val postAdapter by lazy {
        GeneralRecyclerViewAdapter(
            diffCallback,
            R.layout.viewholder_movie, listMovie,
            { _, _, _ ->
                //                homeViewModel.changeTestActive()
//                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
//                findNavController().navigate(
//                    R.id.action_navigation_post_to_detailPostActivity,
//                    bundleOf("title" to movie.title)
//                )
            },
            { movie, view ->
                with(view) {
                    tv_movie_title.text = movie.title
                    tv_release_date.text = movie.releaseDate
                    tv_rating.text = movie.voteAverage.toString()
                    tv_description.text = movie.overview
                    iv_poster.loadImage(BuildConfig.POSTER_URL + movie.posterPath, context)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rv_post) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
        homeViewModel.liveDataNowPlaying.onChangeValue {
            //            listPost.addAll(it)
            postAdapter.setData(it)
        }

        homeViewModel.observeIsFirst().value?.let {
            if (!it) homeViewModel.getNowPlaying()
        }

        homeViewModel.observetestActive().onChangeValue {
            Log.d("logd", "cekActive Changed: $it")
        }

        homeViewModel.observeIsFirst().onChangeValue {
            Log.d("logd", "observeIsFirst: $it")
        }
    }
}