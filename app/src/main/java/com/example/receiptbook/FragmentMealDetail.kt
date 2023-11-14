package com.example.receiptbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.receiptbook.api.ApiObject
import com.example.receiptbook.databinding.FragmentMealDetailBinding
import com.example.recept.adapter.model.MealOne
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentMealDetail : Fragment() {


    val args: FragmentMealDetailArgs by navArgs()
    private var _binding: FragmentMealDetailBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            val meal = ApiObject.getRecipeById(args.idMeal)

            println(meal)
            val picasso = Picasso.Builder(context).build()
            meal?.let {
                picasso
                    .load(meal.ImgSourceUrl)
                    .error(R.drawable.not_found)
                    .into(binding.imageReceipt);
            }

        }
        binding.textIngredients.text = args.idMeal
    }
}