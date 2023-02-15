package com.zaidmansuri.foodrecipe.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaidmansuri.foodrecipe.Model.Category
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.Model.PopularMealModel

import com.zaidmansuri.foodrecipe.RetrifitHelper
import com.zaidmansuri.foodrecipe.RetrofitInterface
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private  var randomMeal=MutableLiveData<Meal>()
    private var popularMeal=MutableLiveData<List<PopularMeal>>()
    private var category=MutableLiveData<List<Category>>()
    val instance= RetrifitHelper.getInstance().create(RetrofitInterface::class.java)

    suspend fun getMeal():LiveData<Meal>{
        val result=instance.getRandom().body()!!.meals[0]
        if(result!=null){
            randomMeal.value=result
        }
        return randomMeal
    }

    suspend fun getPopular(): LiveData<List<PopularMeal>> {
        val result= instance.getPopular("Seafood").body()!!.meals
        if(result!=null){
            popularMeal.value=result
        }
        return  popularMeal
    }

    suspend fun getCategory():LiveData<List<Category>>{
        val result=instance.getCategory().body()!!.categories
        if(result!=null){
            category.value=result
        }
        return category
    }
}