package com.lupa.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lupa.core.util.intentTo
import com.lupa.homepage.databinding.ActivityHomepageBinding
import com.lupa.shared.profile.ProfileInteractor

class HomepageActivity : AppCompatActivity() {

    private var _binding: ActivityHomepageBinding? = null
    private val binding: ActivityHomepageBinding by lazy {
        requireNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileInteractor = ProfileInteractor.get
        with(binding) {
            homepageBtnCheckUser.setOnClickListener {
                profileInteractor.isUserLogon()
            }

            with(profileInteractor.userStateEventManager) {
                onLoading = {

                }
                onSuccess = {
                    println("Successs.....")
                }
                onFailure = { code, exception ->
                    if (code == 401) {
                        intentTo("com.lupa.profile.data.screen.LoginActivity")
                    }
                    println("code error -> $code")
                    println("message error -> ${exception.localizedMessage}")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}