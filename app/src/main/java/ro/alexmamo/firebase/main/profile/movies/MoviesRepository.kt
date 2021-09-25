package ro.alexmamo.firebase.main.profile.movies

import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.firebase.data.Movie
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.utils.Constants.CLOUD_FIRESTORE
import ro.alexmamo.firebase.utils.Constants.ERROR_MESSAGE
import ro.alexmamo.firebase.utils.Constants.MOVIES_REF
import ro.alexmamo.firebase.utils.Constants.RATING
import ro.alexmamo.firebase.utils.Constants.REALTIME_DATABASE
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    @Named(MOVIES_REF) private val moviesCollRef: CollectionReference,
    @Named(MOVIES_REF) private val moviesDbRef: DatabaseReference,
) {
    fun getMoviesFrom(productName: String) = flow {
        try {
            emit(Loading)
            val movies = when (productName) {
                CLOUD_FIRESTORE -> getMoviesFromCloudFirestore()
                REALTIME_DATABASE -> getMoviesFromRealtimeDatabase()
                else -> throw AssertionError()
            }
            emit(Success(movies))
        } catch (e: Exception) {
            emit(Failure(e.message ?: ERROR_MESSAGE))
        }
    }

    private suspend fun getMoviesFromCloudFirestore(): List<Movie> {
        return moviesCollRef.orderBy(RATING, DESCENDING).get().await().toObjects(Movie::class.java)
    }

    private suspend fun getMoviesFromRealtimeDatabase(): List<Movie> {
        return moviesDbRef.orderByChild(RATING).get().await().children.mapNotNull { doc ->
            doc.getValue(Movie::class.java)
        }
    }
}