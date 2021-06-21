package ro.alexmamo.firebase.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import ro.alexmamo.firebase.R
import ro.alexmamo.firebase.data.Response.*
import ro.alexmamo.firebase.databinding.ActivityAuthBinding
import ro.alexmamo.firebase.utils.Actions.Companion.print
import ro.alexmamo.firebase.utils.Constants.MAIN_INTENT
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    @Named(MAIN_INTENT)
    @Inject lateinit var mainIntent: Intent
    @Inject lateinit var signInIntent: Intent
    private lateinit var dataBinding: ActivityAuthBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        initResultLauncher()
    }

    private fun initResultLauncher() {
        resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = getSignedInAccountFromIntent(result.data)
                try {
                    val googleSignInAccount = task.getResult(ApiException::class.java)
                    googleSignInAccount?.apply {
                        idToken?.let { idToken ->
                            signInWithGoogle(idToken)
                        }
                    }
                } catch (e: ApiException) {
                    print(e.message)
                }
            }
        }
    }

    fun openLauncher(v: View) {
        if (v.id == R.id.google_sign_in_button) {
            resultLauncher.launch(signInIntent)
        }
    }

    private fun signInWithGoogle(idToken: String) {
        viewModel.signInWithGoogle(idToken).observe(this, { response ->
            when(response) {
                is Loading -> dataBinding.progressBar.show()
                is Success -> {
                    val isNewUser = response.data
                    if (isNewUser) {
                        createUser()
                    } else {
                        goToMainActivity()
                        dataBinding.progressBar.hide()
                    }
                }
                is Failure -> {
                    print(response.errorMessage)
                    dataBinding.progressBar.hide()
                }
            }
        })
    }

    private fun createUser() {
        viewModel.createUser().observe(this, { response ->
            when(response) {
                is Loading -> dataBinding.progressBar.show()
                is Success -> {
                    goToMainActivity()
                    dataBinding.progressBar.hide()
                }
                is Failure -> {
                    print(response.errorMessage)
                    dataBinding.progressBar.hide()
                }
            }
        })
    }

    private fun goToMainActivity() {
        startActivity(mainIntent)
        finish()
    }
}