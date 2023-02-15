package com.zaidmansuri.foodrecipe.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.CategoryDetail
import com.zaidmansuri.foodrecipe.Model.Category
import com.zaidmansuri.foodrecipe.databinding.CatagoryCardBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.viewHolder>() {

    private var categoryList=ArrayList<Category>()

    fun setCategoryList(categoryList:ArrayList<Category>){
        this.categoryList=categoryList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=CatagoryCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(holder.itemView).load(categoryList[position].strCategoryThumb).into(holder.binding.categoryImg)
        holder.binding.categoryName.text=categoryList[position].strCategory

        holder.binding.categoryImg.setOnClickListener(){
            val intent=Intent(holder.binding.categoryImg.context,CategoryDetail::class.java)
            intent.putExtra("category",categoryList[position].strCategory)
            holder.binding.categoryImg.context.startActivity(intent)
        }
    }

    class viewHolder(val binding: CatagoryCardBinding) : ViewHolder(binding.root){

    }

}