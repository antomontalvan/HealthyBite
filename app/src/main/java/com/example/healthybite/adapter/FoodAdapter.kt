package com.example.healthybite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthybite.databinding.ItemFoodBinding
import com.example.healthybite.model.Food

class FoodAdapter(
    private var foods: List<Food>
) : RecyclerView.Adapter<FoodViewHolder>() {
    //For each object Food on the list, it will execute onCreateViewHolder & onBindViewHolder
    //Executes when the RecyclerView needs to create a new row. Connects with FoodViewHolder > item_food.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): FoodViewHolder {

        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    //We get the quantity of food items = last position of the List of foods
    override fun getItemCount(): Int = foods.size

    //Executes to fill in the row with data.
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        //Gets the last/new object of the list foods.
        val food = foods[position]

        // Fills the textView with the data obtained from the object.
        with(holder.binding){
            tvItemName.text = food.name
            tvItemCategory.text = food.category
            //Rounds up to 2 decimals
            tvItemCalories.text = String.format("%.2f kcal", food.calories)
        }
    }
    //Executed when a new food is added to the list. Replaces the actual list for a new one and notifies the RecyclerView.
    fun updateFoods(newFoods: List<Food>) {

        foods = newFoods
        //Notifies the RecyclerView the list changed; it has to redraw again. Inherited from RecyclerView.Adapter
        notifyDataSetChanged()
    }
}