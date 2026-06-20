package com.example.healthybite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthybite.model.Food

//object because we want to create a unique instance.
object FoodRepository {
    //Real List where we will save food
    private val foodsList = mutableListOf<Food>()
    //MutableLiveData: box with data that can change. Private variable: nobody from outside the repository should modify it.
    //Only ViewModel can modify the information.
    private val foodsLiveData = MutableLiveData<List<Food>>()

    //Executes when the object is created. foodsLiveData starts as an empty list instead of null.
    init {
        foodsLiveData.value = foodsList
    }

    //Returns LiveData
    fun getFoods(): LiveData<List<Food>> {
        return foodsLiveData
    }

    fun addFood(food: Food) {
        //We add the new food to the "real list"
        foodsList.add(food)
        //Updating foodsLiveData. --> RecyclerView can see this. Automatic update of the DiaryActivity.
        //.toList() --> generates List<Food> of "read only"
        foodsLiveData.value = foodsList.toList()
    }
}