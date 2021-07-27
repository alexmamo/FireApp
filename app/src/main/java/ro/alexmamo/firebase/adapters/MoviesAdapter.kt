package ro.alexmamo.firebase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.alexmamo.firebase.BR
import ro.alexmamo.firebase.adapters.MoviesAdapter.MovieViewHolder
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.databinding.MovieDataBinding

class MoviesAdapter(
    private val onMovieClickListener: OnMovieClickListener
) : ListAdapter<Movie, MovieViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = MovieDataBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = currentList[position]
        holder.bindMovie(movie)
    }

    override fun getItemCount() = currentList.size

    private class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldMovie: Movie, newMovie: Movie) = oldMovie.id == newMovie.id

        override fun areContentsTheSame(oldMovie: Movie, newMovie: Movie) = oldMovie == newMovie
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