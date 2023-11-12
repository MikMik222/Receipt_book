package com.example.recept.adapter.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("strMeal") val name: String,
    @SerializedName("strInstructions") val instructions: String
)
