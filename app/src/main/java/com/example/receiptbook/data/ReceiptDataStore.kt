package com.example.receiptbook.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recept.adapter.model.MealOne
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val PREFERENCES_STORE_NAME = "my_store"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_STORE_NAME
)

class ReceiptDataStore (context: Context) {

    private val MEAL_LIST_KEY = stringPreferencesKey("meal_list_key")


    suspend fun saveMealValue(value: MealOne, context: Context?) {
        val gson = Gson()

        context?.let { ctx ->
            val existingList = readMealListFromDataStore(ctx)?.first()?.toMutableList()
            existingList?.add(value)
            val jsonString = gson.toJson(existingList)
            ctx.dataStore.edit { preferences ->
                preferences[MEAL_LIST_KEY] = jsonString
            }
        }
    }

    fun readMealListFromDataStore(context: Context?): Flow<List<MealOne>>? {
        val gson = Gson()

        return context?.let { ctx ->

            ctx.dataStore.data.map { preferences ->
                val jsonString = preferences[MEAL_LIST_KEY] ?: return@map emptyList<MealOne>()

                val type = object : TypeToken<List<MealOne>>() {}.type
                val mealList: List<MealOne> = gson.fromJson(jsonString, type)
                println(mealList.size)
                mealList
            }
        }
    }


}