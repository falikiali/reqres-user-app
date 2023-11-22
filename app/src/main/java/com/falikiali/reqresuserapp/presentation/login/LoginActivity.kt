package com.falikiali.reqresuserapp.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.falikiali.reqresuserapp.presentation.main.MainActivity
import com.falikiali.reqresuserapp.databinding.ActivityLoginBinding
import com.falikiali.reqresuserapp.helper.Constants
import com.falikiali.reqresuserapp.helper.ResultState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBar()
        observeViewModel()
        actionBtnLogin()
    }

    private fun setStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun observeViewModel() {
        with(viewModel) {
            loginState.observe(this@LoginActivity) {
                with(binding) {
                    loading.isVisible = it is ResultState.Loading
                    btnLogin.isVisible = it !is ResultState.Loading
                }

                if (it is ResultState.Success) {

                    val edit = sharedPreferences.edit()
                    edit.putString(Constants.TOKEN, it.data.token)
                    edit.apply()

                    val i = Intent(this@LoginActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(i)
                } else if (it is ResultState.Failed) {
                    showSnackBar(it.error)
                }
            }
        }
    }

    private fun actionBtnLogin() {
        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.login(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}