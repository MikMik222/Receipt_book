package com.example.receiptbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptbook.R

class IngredientRecyclerViewAdapter :
    RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientViewHolder>() {

    private val ingredientList: MutableList<Ingredient> = arrayListOf()
    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientNameTextView: TextView = itemView.findViewById(R.id.ingredientNameTextView)
        val quantityEditText: TextView = itemView.findViewById(R.id.quantityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_meal_ingredients, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val currentIngredient = ingredientList.get(position)

        holder.ingredientNameTextView.text = currentIngredient.name
        holder.quantityEditText.text = currentIngredient.quantity
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun addIngredient(ingredient: Ingredient) {
        ingredientList.add(ingredient)
        println(ingredient)
        notifyItemInserted(ingredientList.size - 1)
    }
}