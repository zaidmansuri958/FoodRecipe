package com.zaidmansuri.foodrecipe.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaidmansuri.foodrecipe.Adapter.CategoryDetailAdapter
import com.zaidmansuri.foodrecipe.Adapter.FavouriteAdapter
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.ViewModel.FavouriteViewModel
import com.zaidmansuri.foodrecipe.ViewModel.FavouriteViewModelFactory

import com.zaidmansuri.foodrecipe.databinding.FragmentFavouriteBinding
import com.zaidmansuri.foodrecipe.db.MealDataBase

class FavouriteFragment : Fragment() {
    private lateinit var binding:FragmentFavouriteBinding
    private lateinit var mealDataBase: MealDataBase
    private lateinit var viewModel:FavouriteViewModel
    private lateinit var favouriteAdapter:FavouriteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealDataBase= activity?.let { MealDataBase.getInstance(it) }!!
        val favouriteViewModelFactory=FavouriteViewModelFactory(mealDataBase)
        viewModel=ViewModelProvider(this,favouriteViewModelFactory).get(FavouriteViewModel::class.java)
        favouriteAdapter= FavouriteAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllData().observe(viewLifecycleOwner) { favouriteList ->
            favouriteAdapter.setFavourites(favouriteData = favouriteList as ArrayList<Meal>)
            binding.favouriteRecycle.apply {
                layoutManager =
                    LinearLayoutManager(activity)
                adapter = favouriteAdapter
            }
        }


    }

}