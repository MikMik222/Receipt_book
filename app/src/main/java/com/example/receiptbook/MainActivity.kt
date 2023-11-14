package com.example.receiptbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.receiptbook.api.ApiObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiObject.makeApiCallToMenu()
        GlobalScope.launch {
            testApi()
        }
    }

    suspend fun testApi(){
        val ne = ApiObject.getRecipeById("52772")
        println(ne)
    }
}