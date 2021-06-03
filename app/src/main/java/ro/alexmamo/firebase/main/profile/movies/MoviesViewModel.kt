package ro.alexmamo.firebase.main.profile.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {
    fun getMoviesFrom(productName: String) = liveData(Dispatchers.IO) {
        repository.getMoviesFrom(productName).collect { response ->
            emit(response)
        }
    }
}