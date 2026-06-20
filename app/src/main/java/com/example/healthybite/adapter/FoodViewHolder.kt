package com.example.healthybite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.healthybite.databinding.ItemFoodBinding

//The ViewHolder represents a row from the RecyclerView.
//[Banana, Snack, 120 kcal] -> ViewHolder
//Connecting each row from the recyclerView with the item_food layout.
class FoodViewHolder(
    val binding: ItemFoodBinding
) : RecyclerView.ViewHolder(binding.root)
//"The view in this row is the root of the item_food.xml layout"