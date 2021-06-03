package ro.alexmamo.firebase.main

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth
) {
    fun signOut() = flow {
        emit(Response.Loading())
        googleSignInClient.signOut().await()
        auth.signOut()
        emit(Response.Success(true))
    }. catch { error ->
        error.message?.let { errorMessage ->
            emit(Response.Failure(errorMessage))
        }
    }

    @ExperimentalCoroutinesApi
    fun getFirebaseAuthState() = callbackFlow  {
        val authStateListener = AuthStateListener { auth ->
            offer(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}