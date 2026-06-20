package com.example.healthybite.repository

import android.content.Context
import androidx.core.content.edit

class PreferencesManager(
    context: Context
) {

    private val preferences =
        context.getSharedPreferences("healthy_bite_prefs", Context.MODE_PRIVATE)

    fun saveUserName(name: String) {
        //Save the user_name in SharedPreferences
        preferences.edit { putString("user_name", name) }
    }

    fun getUserName(): String {

        return preferences.getString("user_name", "") ?: ""
    }

    fun saveTotalCalories(totalCalories: Double) {

        preferences.edit { putFloat("total_calories", totalCalories.toFloat()) }
    }

    fun getTotalCalories(): Double {

        return preferences.getFloat("total_calories", 0f).toDouble()
    }

    fun addCalories(calories: Double) {

        val currentCalories = getTotalCalories()

        saveTotalCalories(currentCalories + calories)
    }
}