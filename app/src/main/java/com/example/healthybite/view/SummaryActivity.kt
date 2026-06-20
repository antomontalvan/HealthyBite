package com.example.healthybite.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.healthybite.databinding.ActivitySummaryBinding
import com.example.healthybite.model.Food
import com.example.healthybite.repository.PreferencesManager
import com.example.healthybite.viewmodel.FoodViewModel

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    private val viewModel: FoodViewModel by viewModels()

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = PreferencesManager(this)

        //Getting back data from Intent.
        val name = intent.getStringExtra("name")!!
        val category = intent.getStringExtra("category")!!
        val calories = intent.getDoubleExtra("calories", 0.0)
        val processed = intent.getBooleanExtra("processed", false)

        //Using function calculateCalories from viewModel
        val totalCalories = viewModel.calculateCalories(calories, processed)

        //Showing the data
        binding.tvFoodName.text = name
        binding.tvCategory.text = category
        binding.tvCalories.text = String.format("%.2f kcal", totalCalories)

        //Set color RED if the calories are greater than 500.
        if (totalCalories > 500) {
            binding.tvCalories.setTextColor(Color.RED)
        }

        binding.btnConfirm.setOnClickListener {

            //Creating object Food with all the data from the Intent.
            val food = Food(
                name,
                category,
                totalCalories,
                processed
            )

            viewModel.addFood(food)

            preferencesManager.addCalories(totalCalories)

            finish()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}