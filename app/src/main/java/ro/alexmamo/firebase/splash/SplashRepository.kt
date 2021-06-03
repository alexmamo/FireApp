package ro.alexmamo.firebase.splash

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    val isUserAuthenticatedInFirebase get() = auth.currentUser != null
}