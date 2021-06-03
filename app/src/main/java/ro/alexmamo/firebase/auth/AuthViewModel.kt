package ro.alexmamo.firebase.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    fun signInWithGoogle(idToken: String) = liveData(Dispatchers.IO) {
        repository.firebaseSignInWithGoogle(idToken).collect { response ->
            emit(response)
        }
    }
}