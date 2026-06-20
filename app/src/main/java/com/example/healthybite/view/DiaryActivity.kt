package com.example.healthybite.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthybite.adapter.FoodAdapter
import com.example.healthybite.databinding.ActivityDiaryBinding
import com.example.healthybite.repository.PreferencesManager
import com.example.healthybite.viewmodel.FoodViewModel

class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding
    private val viewModel: FoodViewModel by viewModels()
    private lateinit var adapter: FoodAdapter
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = PreferencesManager(this)

        loadTotalCalories()

        setupRecyclerView()
        observeFoods()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()

        loadTotalCalories()
    }

    private fun setupRecyclerView() {
        //As we still dont know which foods there are, it starts with an empty list.
        adapter = FoodAdapter(emptyList())
        //Shows the items one below the other.
        binding.rvFoods.layoutManager = LinearLayoutManager(this)
        //connects the RecyclerView with the FoodAdapter
        binding.rvFoods.adapter = adapter
    }

    private fun observeFoods() {

        viewModel.foods.observe(this) { foods ->

            adapter.updateFoods(foods)
        }
    }

    private fun setupListeners(){

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun loadTotalCalories() {

        binding.tvTotalCalories.text =
            String.format(
                "Calorías acumuladas: %.2f kcal",
                preferencesManager.getTotalCalories()
            )
    }
}