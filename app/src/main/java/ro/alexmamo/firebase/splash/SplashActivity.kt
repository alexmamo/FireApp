package ro.alexmamo.firebase.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.firebase.utils.Constants.AUTH_INTENT
import ro.alexmamo.firebase.utils.Constants.MAIN_INTENT
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Named(AUTH_INTENT) @Inject lateinit var authIntent: Intent
    @Named(MAIN_INTENT) @Inject lateinit var mainIntent: Intent
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserIsAuthenticated()
    }

    private fun checkIfUserIsAuthenticated() {
        if (viewModel.isUserAuthenticated) {
            goToMainActivity()
        } else {
            goToAuthInActivity()
        }
    }

    private fun goToMainActivity() {
        startActivity(mainIntent)
        finish()
    }

    private fun goToAuthInActivity() {
        startActivity(authIntent)
        finish()
    }
}