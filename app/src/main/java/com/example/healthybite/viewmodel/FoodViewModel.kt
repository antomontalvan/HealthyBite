package com.example.healthybite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.healthybite.model.Food
import com.example.healthybite.repository.FoodRepository

class FoodViewModel : ViewModel() {

    // getFoods() returns LiveData: this is an observable container.
    // Activities can observe changes but cannot modify the data.
    val foods = FoodRepository.getFoods()

    //FUNCTION -> CALCULATE CALORIES
    fun calculateCalories(
        baseCalories: Double,
        processed: Boolean
    ): Double {

        return if(processed)
            baseCalories * 1.10
        else
            baseCalories
    }

    //FUNCTION -> ADD FOOD
    fun addFood(food: Food) {
        FoodRepository.addFood(food)
    }
}