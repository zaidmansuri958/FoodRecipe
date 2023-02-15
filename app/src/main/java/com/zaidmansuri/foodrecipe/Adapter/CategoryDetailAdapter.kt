package com.zaidmansuri.foodrecipe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.Model.PopularMealModel
import com.zaidmansuri.foodrecipe.databinding.CategoryDetailCardBinding

class CategoryDetailAdapter: RecyclerView.Adapter<CategoryDetailAdapter.viewHolder>() {

    private var CategoryDetailList=ArrayList<PopularMeal>()

    fun setCategoryList(CategoryList:ArrayList<PopularMeal>){
        this.CategoryDetailList=CategoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=CategoryDetailCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return CategoryDetailList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(holder.itemView).load(CategoryDetailList[position].strMealThumb).into(holder.binding.itemImg)
        holder.binding.itemName.text=CategoryDetailList[position].strMeal
    }
    class viewHolder(val binding: CategoryDetailCardBinding):ViewHolder(binding.root){

    }
}