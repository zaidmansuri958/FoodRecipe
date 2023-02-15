package com.zaidmansuri.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaidmansuri.foodrecipe.Adapter.CategoryDetailAdapter
import com.zaidmansuri.foodrecipe.Model.PopularMeal
import com.zaidmansuri.foodrecipe.Model.PopularMealModel
import com.zaidmansuri.foodrecipe.ViewModel.CategoryDetailViewModel
import com.zaidmansuri.foodrecipe.databinding.ActivityCategoryDetailBinding
import kotlinx.coroutines.launch

class CategoryDetail : AppCompatActivity() {
    private lateinit var binding:ActivityCategoryDetailBinding
    private lateinit var viewModel:CategoryDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent=intent
        val category=intent.getStringExtra("category")
        viewModel=ViewModelProvider(this).get(CategoryDetailViewModel::class.java)
        val categoryAdapter:CategoryDetailAdapter= CategoryDetailAdapter()


        lifecycleScope.launch {
            if (category != null) {
                viewModel.getCategoryDetail(category).observe(this@CategoryDetail,object:Observer<List<PopularMeal>>{
                    override fun onChanged(t: List<PopularMeal>?) {
                        binding.categoryDetailRecycle.apply {
                            layoutManager=LinearLayoutManager(context)
                            categoryAdapter.setCategoryList(t as ArrayList<PopularMeal>)
                            adapter=categoryAdapter
                        }
                    }

                })
            }
        }




    }
}