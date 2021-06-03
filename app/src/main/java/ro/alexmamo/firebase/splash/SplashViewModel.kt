package ro.alexmamo.firebase.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SplashRepository
): ViewModel() {
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase
}