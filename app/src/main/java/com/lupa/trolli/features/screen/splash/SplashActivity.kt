package com.lupa.trolli.features.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.lupa.core.util.intentTo
import com.lupa.trolli.R
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progress_circular)
    }

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        /*val userManager = homePageViewModel.userManager //base paging misalnya*/
        val userManager = splashViewModel.userManager
        userManager.onLoading = {
            progressBar.isVisible = true

        }
        userManager.onSuccess = { user ->
            progressBar.isVisible = false
            //Toast.makeText(this, "must be intent to main", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

            intentTo("com.lupa.homepage.HomepageActivity")
            finish()
        }
        userManager.onFailure = {code, throwable ->
            progressBar.isVisible = false
            //throw throwable
            intentTo("com.lupa.profile.data.screen.LoginActivity")
            //intentTo(LoginActivity::class.java.name)
        }
        splashViewModel.getUser()
    }
}