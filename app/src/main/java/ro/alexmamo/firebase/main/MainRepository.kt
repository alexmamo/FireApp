package ro.alexmamo.firebase.main

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.utils.Constants.ERROR_MESSAGE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth
) {
    fun signOut() = flow {
        try {
            emit(Loading)
            googleSignInClient.signOut().await().also {
                emit(Success(it))
            }
            auth.signOut()
        } catch (e: Exception) {
            emit(Failure(e.message ?: ERROR_MESSAGE))
        }
    }

    @ExperimentalCoroutinesApi
    fun getFirebaseAuthState() = callbackFlow  {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}