package ro.alexmamo.firebase.main.profile.movies

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {
    fun getMoviesFrom(productName: String) = repository.getMoviesFrom(productName)
}