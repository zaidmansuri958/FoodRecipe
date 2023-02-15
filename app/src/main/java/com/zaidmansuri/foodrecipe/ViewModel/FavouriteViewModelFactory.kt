package com.zaidmansuri.foodrecipe.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zaidmansuri.foodrecipe.db.MealDataBase

class FavouriteViewModelFactory(private val mealDataBase: MealDataBase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(mealDataBase) as T
    }
}