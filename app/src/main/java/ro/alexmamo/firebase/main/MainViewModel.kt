package ro.alexmamo.firebase.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    fun signOut() = repository.signOut()

    @ExperimentalCoroutinesApi
    fun getAuthState() = repository.getFirebaseAuthState()
}