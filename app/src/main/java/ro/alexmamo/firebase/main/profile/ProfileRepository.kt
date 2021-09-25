package ro.alexmamo.firebase.main.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.data.User
import ro.alexmamo.firebase.utils.Constants.ERROR_MESSAGE
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
        try {
            emit(Loading)
            auth.currentUser?.apply {
                val user = usersRef.document(uid).get().await().toObject(User::class.java)
                user?.let {
                    emit(Success(user))
                }
            }
        } catch (e: Exception) {
            emit(Failure(e.message ?: ERROR_MESSAGE))
        }
    }
}