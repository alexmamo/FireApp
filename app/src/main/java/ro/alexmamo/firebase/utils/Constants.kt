package ro.alexmamo.firebase.utils

import ro.alexmamo.firebase.R
import ro.alexmamo.firebase.data.Product

object Constants {
    //App
    const val TAG = "FirebaseTag"

    //Intents
    const val SPLASH_INTENT = "splashIntent"
    const val AUTH_INTENT = "authIntent"
    const val MAIN_INTENT = "mainIntent"

    //Database Types
    const val PRODUCT_NAME = "productName"
    const val CLOUD_FIRESTORE = "Cloud Firestore"
    const val REALTIME_DATABASE = "Realtime Database"

    //References
    const val USERS_REF = "users"
    const val MOVIES_REF = "movies"

    //Fields
    const val NAME = "name"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"
    const val RATING = "rating"

    //Bindings
    const val MOVIE_POSTER = "moviePoster"
    const val PRODUCT_LOGO = "productLogo"

    //Firebase Products
    val PRODUCTS = listOf(
        Product(R.drawable.cloud_firestore_logo, CLOUD_FIRESTORE, R.id.movies_fragment),
        Product(R.drawable.realtime_database_logo, REALTIME_DATABASE, R.id.movies_fragment)
    )
}