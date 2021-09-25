 package ro.alexmamo.firebase.main.profile.movies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.alexmamo.firebase.R
import ro.alexmamo.firebase.adapters.MoviesAdapter
import ro.alexmamo.firebase.adapters.MoviesAdapter.OnMovieClickListener
import ro.alexmamo.firebase.base.BaseFragment
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.databinding.FragmentMoviesBinding
import ro.alexmamo.firebase.main.MainActivity
import ro.alexmamo.firebase.utils.Actions.Companion.print
import ro.alexmamo.firebase.utils.Constants.MOVIE
import ro.alexmamo.firebase.utils.Constants.PRODUCT_NAME

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MoviesFragment: BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate), OnMovieClickListener {
    private var adapter = MoviesAdapter(this)
    private val viewModel by viewModels<MoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getProductNameFromBundle()
        setMoviesAdapter()
    }

    private fun getProductNameFromBundle() {
        arguments?.getString(PRODUCT_NAME)?.let { productName ->
            setToolbarTitle(productName)
            getMovies(productName)
        }
    }

    private fun setMoviesAdapter() {
        dataBinding.moviesRecyclerView.adapter = adapter
    }

    private fun setToolbarTitle(productName: String) {
        (activity as MainActivity).supportActionBar?.title = productName
    }

    private fun getMovies(productName: String) {
        viewModel.getMoviesFrom(productName).observe(viewLifecycleOwner, { response ->
            when(response) {
                is Loading -> dataBinding.progressBar.show()
                is Success -> {
                    adapter.submitList(response.data)
                    dataBinding.progressBar.hide()
                }
                is Failure -> {
                    print(response.errorMessage)
                    dataBinding.progressBar.hide()
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        findNavController().navigate(R.id.movie_fragment, bundleOf(MOVIE to movie))
    }
}