package com.example.receiptbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.receiptbook.adapterSaved.GridAdapterSaved
import com.example.receiptbook.data.ReceiptDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FragmentSaved : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vie = inflater.inflate(R.layout.fragment_saved, container, false)

        val gridView: GridView = vie.findViewById(R.id.grid_view)

        context?.let {

            lifecycleScope.launch {
                val receipt =
                    ReceiptDataStore().readMealListFromDataStore(context)?.first()?.toMutableList()

                if (receipt != null) {
                    val adapter = context?.let { GridAdapterSaved(it, receipt) }
                    gridView.adapter = adapter
                }

            }
        }

        gridView.setOnItemClickListener { _, _, position, _ ->
            val action =
                FragmentSavedDirections.actionFragmentSavedToFragmentMealDetailSaved(
                    position
                )
            findNavController().navigate(action)

        }
        return vie
    }

}