package com.example.receiptbook.adapter

import java.util.ArrayList

object IngredientItems {
    val ITEMS: MutableList<Ingredient> = ArrayList()

    fun addItem(item:Ingredient){
        ITEMS.add(item)
    }
}