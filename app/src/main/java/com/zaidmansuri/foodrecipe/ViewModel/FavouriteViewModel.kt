package com.zaidmansuri.foodrecipe.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.db.MealDataBase
import kotlinx.coroutines.launch

class FavouriteViewModel (val mealDataBase: MealDataBase): ViewModel() {
    private var randomMeal = MutableLiveData<Meal>()


    fun getAllData(): LiveData<List<Meal>> {
        return mealDataBase.mealDAO().getAllMeal()
    }
}