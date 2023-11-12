package com.example.recept.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.receiptbook.R
import com.example.recept.adapter.model.Meal


class GridAdapter(private val context: Context, private val data: MutableList<Meal>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.grid_item, parent, false)

        view.findViewById<TextView>(R.id.item_name).text = data[position].name

        return view
    }
}