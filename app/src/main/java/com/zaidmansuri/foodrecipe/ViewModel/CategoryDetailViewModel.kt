package com.zaidmansuri.foodrecipe.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.Model.PopularMealModel
import com.zaidmansuri.foodrecipe.RetrifitHelper
import com.zaidmansuri.foodrecipe.RetrofitInterface

class CategoryDetailViewModel:ViewModel() {
    private val categoryDetail=MutableLiveData<List<PopularMeal>>()
    private val instance=RetrifitHelper.getInstance().create(RetrofitInterface::class.java)

    suspend fun getCategoryDetail(category:String):LiveData<List<PopularMeal>>{
        val result=instance.getCategoryItems(category).body()!!.meals
        if(result!=null){
            categoryDetail.value=result
        }
        return categoryDetail
    }


}