package com.zaidmansuri.foodrecipe.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.databinding.CategoryDetailCardBinding

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.viewHolder>() {
    private var favouriteData=ArrayList<Meal>()
    fun setFavourites(favouriteData: ArrayList<Meal>){
        this.favouriteData=favouriteData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=CategoryDetailCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouriteData.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(holder.itemView).load(favouriteData[position].strMealThumb).into(holder.binding.itemImg)
        holder.binding.itemName.text=favouriteData[position].strMeal
    }

    class viewHolder(val binding:CategoryDetailCardBinding):ViewHolder(binding.root){

    }

}