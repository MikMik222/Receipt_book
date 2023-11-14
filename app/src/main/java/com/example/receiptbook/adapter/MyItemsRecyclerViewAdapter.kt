package com.example.receiptbook.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.receiptbook.R

import com.example.receiptbook.databinding.FragmentItemsBinding
import com.example.recept.adapter.model.Meal

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemsRecyclerViewAdapter private constructor(
    private val values: MutableList<Meal> = mutableListOf()
) : RecyclerView.Adapter<MyItemsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

     internal fun addItems(newItems: Meal) {
        println("ddddddddddddddddd")
        values.add(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.name
        holder.contentView.text = item.instructions
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemName
        val contentView: TextView = binding.itemFavourite


        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    companion object {
        private var instance: MyItemsRecyclerViewAdapter? = null

        fun getInstance(): MyItemsRecyclerViewAdapter {
            return instance ?: synchronized(this) {
                instance ?: MyItemsRecyclerViewAdapter().also { instance = it }
            }
        }
    }

}