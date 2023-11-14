package com.example.receiptbook.api

import android.util.Log
import com.example.receiptbook.adapter.MyItemsRecyclerViewAdapter
import com.example.recept.adapter.model.Meal
import com.example.recept.adapter.model.MealResponse
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

    private val mealDbApi = retrofit.create(MealDbApiService::class.java)

    fun makeApiCall() {
        if (isLoading) return
        isLoading = true
        GlobalScope.launch(Dispatchers.Main) {
            try {
                Log.d("s","ubhijnok")
                for (i in 0..10) {
                    val response = mealDbApi.getRandomMeal()

                    if (response.isSuccessful) {
                        val mealResponse: MealResponse? = response.body()
                        val randomMeals: List<Meal>? = mealResponse?.meals

                        val myItemsRecyclerViewAdapter = MyItemsRecyclerViewAdapter.getInstance()
                        randomMeals?.let {
                            for (meal in it) {
                                myItemsRecyclerViewAdapter.addItems(meal)

//                                Log.d("Meal", "Název receptu: ${meal.name}")
//                                Log.d("Meal", "Postup: ${meal.instructions}")
//                                Log.d("Meal", "------------------------")
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
}
