package ro.alexmamo.firebase.main.profile.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.firebase.adapters.MoviesAdapter
import ro.alexmamo.firebase.adapters.MoviesAdapter.OnMovieClickListener
import ro.alexmamo.firebase.base.BaseFragment
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.data.Response
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.databinding.FragmentMoviesBinding
import ro.alexmamo.firebase.main.MainActivity
import ro.alexmamo.firebase.utils.Actions.Companion.print
import ro.alexmamo.firebase.utils.Constants.PRODUCT_NAME
import ro.alexmamo.firebase.utils.ManageViews.Companion.display
import ro.alexmamo.firebase.utils.ManageViews.Companion.hide

@AndroidEntryPoint
class MoviesFragment: BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate), OnMovieClickListener {
    private lateinit var productName: String
    private var adapter = MoviesAdapter(mutableListOf(), this)
    private val viewModel by viewModels<MoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getProductNameFromBundle()
        setMoviesAdapter()
        setToolbarTitle()
        getMovies()
    }

    private fun getProductNameFromBundle() {
        arguments?.getString(PRODUCT_NAME)?.let { productName ->
            this.productName = productName
        }
    }

    private fun setMoviesAdapter() {
        dataBinding.moviesRecyclerView.adapter = adapter
    }

    private fun setToolbarTitle() {
        (activity as MainActivity).supportActionBar?.title = productName
    }

    private fun getMovies() {
        viewModel.getMoviesFrom(productName).observe(viewLifecycleOwner, { response ->
            when(response) {
                is Loading -> display(dataBinding.progressBar)
                is Success -> {
                    adapter.addMovies(response.data)
                    hide(dataBinding.progressBar)
                }
                is Failure -> {
                    print(response.errorMessage)
                    hide(dataBinding.progressBar)
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        makeText(context, movie.title, LENGTH_SHORT).show()
    }
}