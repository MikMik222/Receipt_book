package com.example.receiptbook.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.receiptbook.ItemsFragmentDirections
import com.example.receiptbook.R
import com.example.receiptbook.api.ApiObject

import com.example.receiptbook.databinding.FragmentItemsBinding
import com.example.recept.adapter.model.MealMenu
import com.squareup.picasso.Picasso


class MyItemsRecyclerViewAdapter private constructor(
    private val values: MutableList<MealMenu> = mutableListOf()
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

     @SuppressLint("NotifyDataSetChanged")
     internal fun addItems(newItems: MealMenu) {
        values.add(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameReceipt.text = item.name
        val picasso = Picasso.Builder(holder.imgReceipt.context).build()
        picasso.load(item.ImgSourceUrl)
            .error(R.drawable.not_found)
            .into(holder.imgReceipt)
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameReceipt: TextView = binding.receiptName
        val imgReceipt: ImageView = binding.receiptImage

        init {
            itemView.setOnClickListener(){
                println(absoluteAdapterPosition)
                itemView.findNavController()
                    .navigate(ItemsFragmentDirections
                        .actionItemsFragmentToFragmentMealDetail(values[absoluteAdapterPosition].id))
            }
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