package ro.alexmamo.firebase.main.profile.movies.movie

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import ro.alexmamo.firebase.base.BaseFragment
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.databinding.FragmentMovieBinding
import ro.alexmamo.firebase.utils.Constants.MOVIE

class MovieFragment: BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getMovieFromBundle()
    }

    private fun getMovieFromBundle() {
        arguments?.getSerializable(MOVIE)?.let { movie ->
            setMovieDataToViews(movie as Movie)
        }
    }

    private fun setMovieDataToViews(movie: Movie) {
        with(dataBinding) {
            Glide.with(requireContext())
                .load(movie.posterUrl)
                .into(moviePosterImageView)
            titleTextView.text = movie.getFullTitle()
            overviewTextView.text = movie.overview
        }
    }
}