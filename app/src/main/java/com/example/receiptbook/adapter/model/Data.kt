package com.example.recept.adapter.model


import java.util.ArrayList

object Data {
    val ITEMS: MutableList<Meal> = ArrayList()

    init {
        addItem(Meal("ss","sss"))
        addItem(Meal("1","2"))
    }

    fun addItem(item: Meal) {
        ITEMS.add(item)
    }

}