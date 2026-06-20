package com.example.healthybite.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.healthybite.databinding.ActivityMainBinding
import com.example.healthybite.repository.PreferencesManager
import com.example.healthybite.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: FoodViewModel by viewModels()

    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesManager = PreferencesManager(this)

        loadUserName()
        loadTotalCalories()
        setupSpinner()
        setupListeners()

    }
    /* fun onResume makes that the total calories are loaded each time the user goes to the main activity. */
    override fun onResume() {
        super.onResume()

        loadTotalCalories()
    }

    private fun loadUserName() {

        val userName = preferencesManager.getUserName()

        binding.tvWelcome.text = "Hola, $userName!"
    }

    private fun loadTotalCalories(){
        val totalCalories = preferencesManager.getTotalCalories()

        binding.tvTotalCalories.text = String.format("Calorias diarias totales: .2f kcal", totalCalories)
    }

    private fun setupSpinner(){
        val categories = listOf(
            "Selecciona una categoria",
            "Desayuno",
            "Almuerzo",
            "Snack",
            "Cena"
        )
        //Creating the adapter for the spinner. The ArrayAdapter connects the list of categories with the Spinner.
        val adapter = ArrayAdapter(
            this, //From which activity are we creating the spinner.
            android.R.layout.simple_spinner_item, //design of each element of the spinner
            categories //List of data to be used
        )
        //Configuring how the list shows when the user touches the spinner
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        //We assign the adapter to the spinner.
        binding.spCategory.adapter = adapter
    }
    private fun setupListeners(){
        binding.btnCalculate.setOnClickListener {
            validateAndContinue()
        }

        binding.btnViewDiary.setOnClickListener {
            val intent = Intent(this, DiaryActivity::class.java)

            startActivity(intent)
        }
    }

    private fun validateAndContinue(){
        //.trim() -> eliminates blank spaces.
        val name = binding.etFoodName.text.toString().trim()
        //text editable -> String -> trim eliminates blank spaces -> Double o null if is empty.
        val calories = binding.etCalories.text.toString().trim().toDoubleOrNull()

        val category = binding.spCategory.selectedItem.toString()

        if (name.isEmpty()) {
            binding.etFoodName.error = "Ingrese el Nombre de la comida."
            return
        }

        if (calories == null || calories <= 0) {
            binding.etCalories.error = "Ingrese un numero de calorias valido."
            return
        }

        if (category == "Selecciona una categoria"){
            Toast.makeText(
                this,
                "Please select a category",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        goToSummary(name, calories, category)
    }

    private fun goToSummary(name: String, calories: Double, category:String){

        val processed = binding.cbProcessed.isChecked

        val intent = Intent(this, SummaryActivity::class.java)

        intent.putExtra("name", name)
        intent.putExtra("calories", calories)
        intent.putExtra("category", category)
        intent.putExtra("processed", processed)

        startActivity(intent)
    }
}