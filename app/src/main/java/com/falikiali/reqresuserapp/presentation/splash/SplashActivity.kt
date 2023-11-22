package com.falikiali.reqresuserapp.presentation.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.view.WindowCompat
import com.falikiali.reqresuserapp.databinding.ActivitySplashBinding
import com.falikiali.reqresuserapp.helper.Constants
import com.falikiali.reqresuserapp.presentation.login.LoginActivity
import com.falikiali.reqresuserapp.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setStatusBar()
        startIntent()
    }

    private fun setStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun startIntent() {
        if (sharedPreferences.getString(Constants.TOKEN, null) != null) {
            Handler(mainLooper).postDelayed({
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
            }, 3500)
        } else {
            Handler(mainLooper).postDelayed({
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(i)
            }, 3500)
        }
    }

}