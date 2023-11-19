package com.example.receiptbook.adapterSaved

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.receiptbook.R
import com.example.recept.adapter.model.MealOne
import com.squareup.picasso.Picasso

class GridAdapterSaved(private val context: Context, private val data: List<MealOne>) : BaseAdapter() {

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
        val picasso = Picasso.Builder(context).build()
        picasso
            .load(data[position].ImgSourceUrl)
            .error(R.drawable.not_found)
            .into(view.findViewById<ImageView>(R.id.item_image))
//        view.findViewById<ImageView>(R.id.item_image).setImageResource(data[position].)
        view.findViewById<TextView>(R.id.item_name).text = data[position].name
//        view.findViewById<TextView>(R.id.item_favourite).text = data[position].rating.toString()

        return view
    }
}