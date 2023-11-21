package com.example.receiptbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.receiptbook.adapter.Ingredient
import com.example.receiptbook.adapter.IngredientRecyclerViewAdapter
import com.example.receiptbook.adapter.model.UpdateModel
import com.example.receiptbook.api.ApiObject
import com.example.receiptbook.data.ReceiptDataStore
import com.example.receiptbook.databinding.FragmentMealDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentMealDetail: Fragment() {

    private var ingredientAdapter: IngredientRecyclerViewAdapter? = null
    val args: FragmentMealDetailArgs by navArgs()
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var receiptDataStore :ReceiptDataStore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        receiptDataStore = context?.let { ReceiptDataStore() }!!
        ingredientAdapter = IngredientRecyclerViewAdapter()
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            if (receiptDataStore.mealInSaved(context, args.idMeal) != null) {

                binding.buttonSaveReceipt.visibility = View.GONE
            }
        }
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientRecyclerView: RecyclerView = view.findViewById(R.id.ingredientRecyclerView)

        ingredientRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ingredientAdapter
        }
        val webView = view.findViewById<WebView>(R.id.webView)
        lifecycleScope.launch(Dispatchers.Main) {
            val meal = ApiObject.getRecipeById(args.idMeal)

            val picasso = Picasso.Builder(context).build()
            meal?.let { it ->
                picasso
                    .load(it.ImgSourceUrl)
                    .error(R.drawable.not_found)
                    .into(binding.imageReceipt)
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

                if(it.strYoutube != null){
                    webView.settings.javaScriptEnabled = true
                    webView.webChromeClient = WebChromeClient()

                    val videoId = it.strYoutube.split("watch?v=").last()
                    val videoUrl = "https://www.youtube.com/embed/$videoId"
                    webView.loadUrl(videoUrl)
                }



                binding.buttonSaveReceipt.setOnClickListener{
                    lifecycleScope.launch {
                        receiptDataStore.saveMealValue(meal,context)
                        binding.buttonSaveReceipt.visibility = View.GONE
                    }

                }
                binding.nameOfRecept.text = it.name
                binding.instructionOfRecept.text = it.strInstructions
            }
        }
    }
}
