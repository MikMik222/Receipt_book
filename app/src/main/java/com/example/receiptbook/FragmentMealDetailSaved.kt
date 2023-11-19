package com.example.receiptbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptbook.adapter.Ingredient
import com.example.receiptbook.adapter.IngredientRecyclerViewAdapter
import com.example.receiptbook.adapter.model.UpdateModel
import com.example.receiptbook.data.ReceiptDataStore
import com.example.receiptbook.databinding.FragmentMealDetailSavedBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class FragmentMealDetailSaved : Fragment() {

    private var ingredientAdapter: IngredientRecyclerViewAdapter? = null
    val args: FragmentMealDetailSavedArgs by navArgs()
    private var _binding: FragmentMealDetailSavedBinding? = null
    private val binding get() = _binding!!
    private lateinit var receiptDataStore: ReceiptDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        receiptDataStore = context?.let { ReceiptDataStore() }!!
        ingredientAdapter = IngredientRecyclerViewAdapter()
        _binding = FragmentMealDetailSavedBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientRecyclerView: RecyclerView = view.findViewById(R.id.ingredientRecyclerView_saved)

        ingredientRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ingredientAdapter
        }

        val webView = view.findViewById<WebView>(R.id.webView_saved)

        val idPosition = args.idPositionInList
        println(idPosition)
        lifecycleScope.launch {
            val meal = receiptDataStore.getMealByIndexInArray(context, idPosition)

            val picasso = Picasso.Builder(context).build()
            meal?.let { it ->
                picasso
                    .load(it.ImgSourceUrl)
                    .error(R.drawable.not_found)
                    .into(binding.imageReceiptSaved)
                val updateModel = UpdateModel()
                val arrayOfIngredients = updateModel.GetListOfIngredients(meal)
                val arrayOfIngredientsMeasure = updateModel.GetListOfIngredientsMeasure(meal)
                for (i in arrayOfIngredients.indices) {
                    if (arrayOfIngredients[i] != "") {
                        ingredientAdapter?.addIngredient(
                            Ingredient(arrayOfIngredients[i], arrayOfIngredientsMeasure[i])
                        )
                    }
                }

                if (it.strYoutube != null) {
                    webView.settings.javaScriptEnabled = true
                    webView.settings.pluginState = WebSettings.PluginState.ON
                    webView.webChromeClient = WebChromeClient()

                    val videoId = it.strYoutube.split("watch?v=").last()
                    val videoUrl = "https://www.youtube.com/embed/$videoId"
                    webView.loadUrl(videoUrl)
                }
            }



            binding.buttonDelete.setOnClickListener {
                lifecycleScope.launch {

                }
            }

        }


    }
}
