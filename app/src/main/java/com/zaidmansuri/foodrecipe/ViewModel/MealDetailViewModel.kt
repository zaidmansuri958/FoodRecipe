package com.zaidmansuri.foodrecipe.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.RetrifitHelper
import com.zaidmansuri.foodrecipe.RetrofitInterface
import com.zaidmansuri.foodrecipe.db.MealDataBase
import kotlinx.coroutines.launch

class MealDetailViewModel(private val mealDatabse:MealDataBase):ViewModel(){
    private var MealBYID =MutableLiveData<Meal>()
    val instance=RetrifitHelper.getInstance().create(RetrofitInterface::class.java)


    suspend fun getMeal(id:String):LiveData<Meal>{
        val result=instance.getBYID(id).body()!!.meals[0]
        if(result!=null){
            MealBYID.value=result
        }
        return MealBYID
    }

     fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabse.mealDAO().insertMeal(meal)
        }
    }

    fun deleteMeal(meal:Meal){
        viewModelScope.launch {
            mealDatabse.mealDAO().deleteMeal(meal)
        }
    }
}