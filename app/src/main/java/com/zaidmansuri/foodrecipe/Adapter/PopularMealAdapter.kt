package com.zaidmansuri.foodrecipe.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.MealDetail
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
        holder.binding.cardImg.setOnClickListener(){
            val intent=Intent(holder.binding.cardImg.context,MealDetail::class.java)
            intent.putExtra("image",mealList[position].strMealThumb)
            intent.putExtra("id",mealList[position].idMeal)
            holder.binding.cardImg.context.startActivity(intent)
        }
    }

    class viewHolder(val binding:RecycleCardBinding) : ViewHolder(binding.root){

    }
}