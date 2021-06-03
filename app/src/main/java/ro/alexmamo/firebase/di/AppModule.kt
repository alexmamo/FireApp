package ro.alexmamo.firebase.di

import android.app.Application
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.alexmamo.firebase.R
import ro.alexmamo.firebase.auth.AuthActivity
import ro.alexmamo.firebase.main.MainActivity
import ro.alexmamo.firebase.splash.SplashActivity
import ro.alexmamo.firebase.utils.Constants.AUTH_INTENT
import ro.alexmamo.firebase.utils.Constants.MAIN_INTENT
import ro.alexmamo.firebase.utils.Constants.SPLASH_INTENT
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Named(SPLASH_INTENT)
    fun provideSplashIntent(context: Context): Intent {
        return Intent(context, SplashActivity::class.java)
    }

    @Provides
    @Named(AUTH_INTENT)
    fun provideAuthIntent(context: Context): Intent {
        return Intent(context, AuthActivity::class.java)
    }

    @Provides
    @Named(MAIN_INTENT)
    fun provideMainIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }

    @Provides
    fun provideGoogleSignInOptions(application: Application): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    @Provides
    fun provideGoogleSignInClient(
        application: Application,
        options: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(application, options)
    }

    @Provides
    fun provideSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
        return googleSignInClient.signInIntent
    }
}