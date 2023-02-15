package com.zaidmansuri.foodrecipe

import androidx.lifecycle.LiveData
import com.zaidmansuri.foodrecipe.Model.CategoryModel
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.Model.PopularMealModel
import com.zaidmansuri.foodrecipe.Model.RandomMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("random.php")
    suspend fun getRandom():Response<RandomMeal>

    @GET("lookup.php?")
    suspend fun getBYID(@Query("i") id:String):Response<RandomMeal>

    @GET("filter.php?")
    suspend fun getPopular(@Query("c") category:String):Response<PopularMealModel>

    @GET("categories.php")
    suspend fun getCategory():Response<CategoryModel>

    @GET("filter.php?")
    suspend fun getCategoryItems(@Query("c") category:String):Response<PopularMealModel>
}