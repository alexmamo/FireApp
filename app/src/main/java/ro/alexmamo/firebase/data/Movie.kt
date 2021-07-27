package ro.alexmamo.firebase.data

import java.io.Serializable
import kotlin.math.abs

data class Movie(
    var id: String? = null,
    var title: String? = null,
    var year: Int? = null,
    var rating: Double? = null,
    var posterUrl: String? = null,
    var overview: String? = null
): Serializable {
    fun getFullTitle() = "$title ($year)"

    fun getRating(rating: Double): String {
        val ratingToDisplay = if (rating < 0) {
            abs(rating)
        } else {
            rating
        }
        return String.format("%.1f", ratingToDisplay)
    }
}