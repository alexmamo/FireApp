package ro.alexmamo.firebase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ro.alexmamo.firebase.BR
import ro.alexmamo.firebase.adapters.MoviesAdapter.MovieViewHolder
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.databinding.MovieDataBinding

class MoviesAdapter(
    val movies: MutableList<Movie>,
    private val onMovieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = MovieDataBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovies(movies: List<Movie>) {
        if (this.movies.isNotEmpty()) {
            this.movies.clear()
        }
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val dataBinding: ViewDataBinding
    ): RecyclerView.ViewHolder(dataBinding.root) {
        fun bindMovie(movie: Movie) {
            with(dataBinding) {
                setVariable(BR.movie, movie)
                setVariable(BR.onMovieClickListener, onMovieClickListener)
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}