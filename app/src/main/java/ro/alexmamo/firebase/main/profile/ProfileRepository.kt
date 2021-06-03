package ro.alexmamo.firebase.main.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Response
import ro.alexmamo.firebase.data.User
import ro.alexmamo.firebase.utils.Constants.USERS_REF
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @Named(USERS_REF) private val usersRef: CollectionReference
) {
    fun getUserFromFirestore() = flow {
        emit(Response.Loading())
        auth.currentUser?.apply {
            val user = usersRef.document(uid).get().await().toObject(User::class.java)
            user?.let {
                emit(Response.Success(user))
            }
        }
    }. catch { error ->
        error.message?.let { errorMessage ->
            emit(Response.Failure(errorMessage))
        }
    }
}