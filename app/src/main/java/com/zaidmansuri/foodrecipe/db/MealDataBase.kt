package com.zaidmansuri.foodrecipe.db

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zaidmansuri.foodrecipe.Model.Meal


@Database(entities = [Meal::class], version = 1)
abstract class MealDataBase :RoomDatabase(){
    abstract fun mealDAO():mealDAO

    companion object{
        @Volatile
        var INSTANCE:MealDataBase? =null;
        @Synchronized
        fun getInstance(context:Context):MealDataBase{
            if(INSTANCE==null){
               INSTANCE=Room.databaseBuilder(context,
               MealDataBase::class.java,
               "MealDB").fallbackToDestructiveMigration().build()
            }
          return INSTANCE as MealDataBase
        }
    }
}