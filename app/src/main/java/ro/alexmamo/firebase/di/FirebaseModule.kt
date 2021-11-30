package ro.alexmamo.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.alexmamo.firebase.utils.Constants.MOVIES_REF
import ro.alexmamo.firebase.utils.Constants.USERS_REF
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Named(USERS_REF)
    fun provideUsersRef(db: FirebaseFirestore) = db.collection(USERS_REF)

    @Provides
    @Named(MOVIES_REF)
    fun provideCloudFirestoreMoviesRef(db: FirebaseFirestore) = db.collection(MOVIES_REF)

    @Provides
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    @Named(MOVIES_REF)
    fun provideRealtimeDatabaseMoviesRef(db: FirebaseDatabase) = db.reference.child(MOVIES_REF)
}