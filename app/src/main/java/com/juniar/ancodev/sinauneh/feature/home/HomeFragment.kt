package com.juniar.ancodev.sinauneh.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.juniar.ancodev.sinauneh.R
import id.nyoman.core.BuildConfig
import id.nyoman.core.State
import id.nyoman.core.base.BaseFragment
import id.nyoman.core.data.MovieModel
import id.nyoman.core.utils.DiffItemCallback
import id.nyoman.core.utils.GeneralPagedListAdapter
import id.nyoman.core.utils.loadImage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.viewholder_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel by viewModel<HomeViewModel>()

    private val movieAdapter by lazy {
        GeneralPagedListAdapter(
            DiffItemCallback<MovieModel>(),
            R.layout.viewholder_movie,
            { _, _, _ -> },
            { movie, view ->
                with(view) {
                    tv_movie_title.text = movie.title
                    tv_release_date.text = movie.releaseDate
                    tv_rating.text = movie.voteAverage.toString()
                    tv_description.text = movie.overview
                    iv_poster.loadImage(BuildConfig.POSTER_URL + movie.posterPath, context)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rv_post) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }

        with(homeViewModel) {
            listMovie.onChangeValue {
                movieAdapter.submitList(it)
            }

            stateLiveData.onChangeValue {
                pb_loading.visibility =
                    if (homeViewModel.listIsEmpty() && it == State.LOADING) View.VISIBLE else View.GONE
                tv_error.visibility =
                    if (homeViewModel.listIsEmpty() && it == State.ERROR) View.VISIBLE else View.GONE
                if (!homeViewModel.listIsEmpty()) {
                    movieAdapter.setState(it)
                }
            }

            observetestActive().onChangeValue {
                Log.d("logd", "cekActive Changed: $it")
            }

            observeIsFirst().onChangeValue {
                Log.d("logd", "observeIsFirst: $it")
            }
        }
    }
}