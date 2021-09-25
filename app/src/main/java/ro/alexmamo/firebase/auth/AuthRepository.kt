package ro.alexmamo.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue.serverTimestamp
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.utils.Constants.CREATED_AT
import ro.alexmamo.firebase.utils.Constants.EMAIL
import ro.alexmamo.firebase.utils.Constants.ERROR_MESSAGE
import ro.alexmamo.firebase.utils.Constants.NAME
import ro.alexmamo.firebase.utils.Constants.PHOTO_URL
import ro.alexmamo.firebase.utils.Constants.USERS_REF
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(USERS_REF) private val usersRef: CollectionReference
) {
    suspend fun firebaseSignInWithGoogle(idToken: String) = flow {
        try {
            emit(Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            authResult.additionalUserInfo?.apply {
                emit(Success(isNewUser))
            }
        } catch (e: Exception) {
            emit(Failure(e.message ?: ERROR_MESSAGE))
        }
    }

    suspend fun createUserInFirestore() = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                usersRef.document(uid).set(mapOf(
                    NAME to displayName,
                    EMAIL to email,
                    PHOTO_URL to photoUrl?.toString(),
                    CREATED_AT to serverTimestamp()
                )).await().also {
                    emit(Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Failure(e.message ?: ERROR_MESSAGE))
        }
    }
}