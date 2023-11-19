package com.example.receiptbook.api


import com.example.recept.adapter.model.MealResponseItem
import com.example.recept.adapter.model.MealResponseMenu
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbApiServiceRandom {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponseMenu>
}

interface MealDbApiServiceOne{
    @GET("lookup.php")
    suspend fun getMealByID(@Query("i") id: String): Response<MealResponseItem>

}

interface MealDbApiServiceSearch{
    @GET("search.php")
    suspend fun getMealByName(@Query("s") id: String): Response<MealResponseItem>

}