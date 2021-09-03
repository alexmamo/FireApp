package ro.alexmamo.firebase.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.alexmamo.firebase.R
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.databinding.ActivityMainBinding
import ro.alexmamo.firebase.utils.Actions.Companion.print
import ro.alexmamo.firebase.utils.Constants.AUTH_INTENT
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Named(AUTH_INTENT) @Inject lateinit var authIntent: Intent
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        setNavController()
        getAuthState()
    }

    private fun setNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun signOut() {
        viewModel.signOut().observe(this, { response ->
            when(response) {
                is Loading -> dataBinding.progressBar.show()
                is Success -> dataBinding.progressBar.hide()
                is Failure -> {
                    print(response.errorMessage)
                    dataBinding.progressBar.hide()
                }
            }
        })
    }

    @ExperimentalCoroutinesApi
    private fun getAuthState() {
        viewModel.getAuthState().observe(this, { isUserSignedOut ->
            if (isUserSignedOut) {
                goToAuthInActivity()
            }
        })
    }

    private fun goToAuthInActivity() {
        startActivity(authIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out_item -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }
}