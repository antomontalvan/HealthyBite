package com.example.healthybite.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.healthybite.databinding.ActivitySplashBinding
import com.example.healthybite.repository.PreferencesManager

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = PreferencesManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            checkUser()
        }, 2000)
    }

    private fun checkUser() {

        val userName = preferencesManager.getUserName()

        if(userName.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))

        } else {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
        finish()
    }
}