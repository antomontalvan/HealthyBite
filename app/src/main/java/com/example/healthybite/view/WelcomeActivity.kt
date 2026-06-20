package com.example.healthybite.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthybite.databinding.ActivityWelcomeBinding
import com.example.healthybite.repository.PreferencesManager

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = PreferencesManager(this)

        setupListeners()
    }

    private fun setupListeners() {

        binding.btnStart.setOnClickListener {

            val userName = binding.etUserName.text.toString().trim()

            if(userName.isEmpty()) {

                binding.etUserName.error = "Ingrese su nombre"
                return@setOnClickListener
            }

            preferencesManager.saveUserName(userName)

            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }
    }
}