package com.example.receiptbook.adapter.model

import com.example.recept.adapter.model.MealOne

class UpdateModel {
    fun GetListOfIngredients (meal : MealOne):List<String> {
        val res = mutableListOf<String>()
        meal.strIngredient1?.let { res.add(it) }
        meal.strIngredient2?.let { res.add(it) }
        meal.strIngredient3?.let { res.add(it) }
        meal.strIngredient4?.let { res.add(it) }
        meal.strIngredient5?.let { res.add(it) }
        meal.strIngredient6?.let { res.add(it) }
        meal.strIngredient7?.let { res.add(it) }
        meal.strIngredient8?.let { res.add(it) }
        meal.strIngredient9?.let { res.add(it) }
        meal.strIngredient10?.let { res.add(it) }
        meal.strIngredient11?.let { res.add(it) }
        meal.strIngredient12?.let { res.add(it) }
        meal.strIngredient13?.let { res.add(it) }
        meal.strIngredient14?.let { res.add(it) }
        meal.strIngredient15?.let { res.add(it) }
        meal.strIngredient16?.let { res.add(it) }
        meal.strIngredient17?.let { res.add(it) }
        meal.strIngredient18?.let { res.add(it) }
        meal.strIngredient19?.let { res.add(it) }
        meal.strIngredient20?.let { res.add(it) }
        return res
    }

    fun GetListOfIngredientsMeasure (meal : MealOne):List<String> {
        val res = mutableListOf<String>()
        meal.strMeasure1?.let { res.add(it) }
        meal.strMeasure2?.let { res.add(it) }
        meal.strMeasure3?.let { res.add(it) }
        meal.strMeasure4?.let { res.add(it) }
        meal.strMeasure5?.let { res.add(it) }
        meal.strMeasure6?.let { res.add(it) }
        meal.strMeasure7?.let { res.add(it) }
        meal.strMeasure8?.let { res.add(it) }
        meal.strMeasure9?.let { res.add(it) }
        meal.strMeasure10?.let { res.add(it) }
        meal.strMeasure11?.let { res.add(it) }
        meal.strMeasure12?.let { res.add(it) }
        meal.strMeasure13?.let { res.add(it) }
        meal.strMeasure14?.let { res.add(it) }
        meal.strMeasure15?.let { res.add(it) }
        meal.strMeasure16?.let { res.add(it) }
        meal.strMeasure17?.let { res.add(it) }
        meal.strMeasure18?.let { res.add(it) }
        meal.strMeasure19?.let { res.add(it) }
        meal.strMeasure20?.let { res.add(it) }
        return res
    }
}