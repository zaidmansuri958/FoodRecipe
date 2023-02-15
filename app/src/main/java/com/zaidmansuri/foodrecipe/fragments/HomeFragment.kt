package com.zaidmansuri.foodrecipe.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.Adapter.CategoryAdapter
import com.zaidmansuri.foodrecipe.Adapter.PopularMealAdapter
import com.zaidmansuri.foodrecipe.MealDetail
import com.zaidmansuri.foodrecipe.Model.*
import com.zaidmansuri.foodrecipe.R
import com.zaidmansuri.foodrecipe.RetrifitHelper
import com.zaidmansuri.foodrecipe.RetrofitInterface
import com.zaidmansuri.foodrecipe.ViewModel.HomeViewModel
import com.zaidmansuri.foodrecipe.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal:LiveData<Meal>
    private lateinit var popularAdapter:PopularMealAdapter
    private lateinit var categoryAdapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        popularAdapter=PopularMealAdapter()
        categoryAdapter= CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            randomMeal=viewModel.getMeal()
            viewModel.getMeal().observe(viewLifecycleOwner,object :Observer<Meal>{
                override fun onChanged(t: Meal?) {
                    Glide.with(view).load(t!!.strMealThumb).into(binding.randomMeal)
                }
            })

            viewModel.getPopular().observe(viewLifecycleOwner) { mealList ->
                popularAdapter.setMeals(mealList = mealList as ArrayList<PopularMeal>)
                binding.popularRecycle.apply {
                    layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    adapter = popularAdapter
                }
            }

            viewModel.getCategory().observe(viewLifecycleOwner) { categoryList ->
                categoryAdapter.setCategoryList(categoryList = categoryList as ArrayList<Category>)
                binding.categoryRecycle.apply {
                    layoutManager = GridLayoutManager(activity,3)
                    adapter=categoryAdapter

                }
            }




        }
        binding.randomMeal.setOnClickListener(){
            val intent:Intent=Intent(activity,MealDetail::class.java)
            intent.putExtra("id",randomMeal.value!!.idMeal.toString())
            intent.putExtra("image",randomMeal.value!!.strMealThumb.toString())
            startActivity(intent)

        }


    }


}