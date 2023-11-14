package com.example.receiptbook.api

import com.example.recept.adapter.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface MealDbApiService {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>
}