package com.example.receiptbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.receiptbook.api.ApiObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiObject.makeApiCall()
    }
}