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
import com.example.receiptbook.R

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
        println("dewtfvubhnijk")
        println(item.ImgSourceUrl)
        picasso.load(item.ImgSourceUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.not_found)
            .into(holder.imgReceipt)
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameReceipt: TextView = binding.receiptName
        val imgReceipt: ImageView = binding.receiptImage

        init {
            itemView.setOnClickListener(){
                println("vgbhkjnlkm" + adapterPosition)
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