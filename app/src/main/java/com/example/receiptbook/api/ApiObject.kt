package com.example.receiptbook.api

import android.util.Log
import com.example.receiptbook.adapter.MyItemsRecyclerViewAdapter
import com.example.recept.adapter.model.MealMenu
import com.example.recept.adapter.model.MealOne
import com.example.recept.adapter.model.MealResponseItem
import com.example.recept.adapter.model.MealResponseMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject {
    private var isLoading = false
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mealDbApiRandom = retrofit.create(MealDbApiServiceRandom::class.java)
    private val mealDbApiById = retrofit.create(MealDbApiServiceOne::class.java)
    private val mealDbApiSearch = retrofit.create(MealDbApiServiceSearch::class.java)

    fun makeApiCallToMenu() {
        if (isLoading) return
        isLoading = true
        val myItemsRecyclerViewAdapter = MyItemsRecyclerViewAdapter.getInstance()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                for (i in 0..10) {
                    val response = mealDbApiRandom.getRandomMeal()

                    if (response.isSuccessful) {
                        val mealResponse: MealResponseMenu? = response.body()
                        val randomMeals: List<MealMenu>? = mealResponse?.meals


                        randomMeals?.let {
                            for (meal in it) {
                                myItemsRecyclerViewAdapter.addItems(meal)
                            }
                        } ?: run {
                            Log.e("Meal", "Nepodařilo se získat náhodné recepty.")
                        }
                    } else {
                        Log.e("Meal", "Chyba při získávání náhodného receptu: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("Meal", "Chyba při získávání náhodného receptu: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    suspend fun getRecipeById(idOfMenu: String): MealOne? {
        try {
            val response = mealDbApiById.getMealByID(idOfMenu)
            if (response.isSuccessful) {
                val mealResponse: MealResponseItem? = response.body()
                val mealById: List<MealOne>? = mealResponse?.meal
                return mealById?.get(0)
            } else {
                Log.e("Mal", "Chyba při získávání náhodného receptu: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("Meal", "Chyba při získávání náhodného receptu: ${e.message}")
        }
        return null
    }

    suspend fun getRecipeByName(name: String): MealOne? {
        try {
            val response = mealDbApiSearch.getMealByName(name)
            if (response.isSuccessful) {
                val mealResponse: MealResponseItem? = response.body()
                val mealById: List<MealOne>? = mealResponse?.meal
                return mealById?.get(0)
            } else {
                Log.e("Mal", "Chyba při získávání náhodného receptu: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("Meal", "Chyba při získávání náhodného receptu: ${e.message}")
        }
        return null
    }
}

