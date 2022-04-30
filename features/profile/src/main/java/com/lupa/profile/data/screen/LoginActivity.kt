package com.lupa.profile.data.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.lupa.core.util.intentTo
import com.lupa.profile.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding: ActivityLoginBinding by lazy {
        requireNotNull(_binding)
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribe()

    }

    private fun setupView() {
        with(binding) {
            profileBtnLogin.setOnClickListener {
                val username = profileEtUsername.text.toString()
                val password = profileEtPassword.text.toString()

                viewModel.requestLogin(username, password)
            }
        }
    }

    private fun subscribe() {
        with(binding) {
            profileLoginProgressbar.isVisible = false
            with(viewModel.loginEventManager) {
                onLoading = {
                    profileLoginProgressbar.isVisible = true
                    profileBtnLogin.isEnabled = false
                }
                onSuccess = {
                    println("anuan -> $it")
                    profileLoginProgressbar.isVisible = false
                    //intent ke main app
                    // save token
                    profileBtnLogin.isEnabled = true
                    viewModel.saveToken(it)
                    intentTo("com.lupa.homepage.HomePageActivity")
                }
                onFailure = { code, ex ->
                    profileLoginProgressbar.isVisible = false
                    profileBtnLogin.isEnabled = true
                    //failure
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}