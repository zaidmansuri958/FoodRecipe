package com.zaidmansuri.foodrecipe.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.databinding.RecycleCardBinding

class PopularMealAdapter: RecyclerView.Adapter<PopularMealAdapter.viewHolder>() {
    private var mealList=ArrayList<PopularMeal>()

    fun setMeals(mealList:ArrayList<PopularMeal>){
        this.mealList=mealList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=RecycleCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.binding.cardImg)
    }

    class viewHolder(val binding:RecycleCardBinding) : ViewHolder(binding.root){

    }
}