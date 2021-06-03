package ro.alexmamo.firebase.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {
    fun getUser() = liveData(Dispatchers.IO) {
        repository.getUserFromFirestore().collect { response ->
            emit(response)
        }
    }
}