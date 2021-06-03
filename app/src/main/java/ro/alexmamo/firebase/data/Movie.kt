package ro.alexmamo.firebase.data

import kotlin.math.abs

data class Movie(
    var title: String? = null,
    var year: Int? = null,
    var rating: Double? = null,
    var posterUrl: String? = null
) {
    fun getFullTitle(title: String, year: Int): String {
        return "$title ($year)"
    }

    fun getRating(rating: Double): String {
        val ratingToDisplay = if (rating < 0) {
            abs(rating)
        } else {
            rating
        }
        return String.format("%.1f", ratingToDisplay)
    }
}