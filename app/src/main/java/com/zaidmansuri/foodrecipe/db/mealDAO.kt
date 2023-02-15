package com.zaidmansuri.foodrecipe.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zaidmansuri.foodrecipe.Model.Meal

@Dao
interface mealDAO {
    @Insert
    suspend fun insertMeal(meal: Meal)

    @Update
    suspend fun updateMeal(meal:Meal)

    @Delete
    suspend fun deleteMeal(meal:Meal)

    @Query("SELECT * FROM MealInformation")
    fun getAllMeal():LiveData<List<Meal>>

}