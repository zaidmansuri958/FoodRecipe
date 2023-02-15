package com.zaidmansuri.foodrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.zaidmansuri.foodrecipe.Model.Meal
import com.zaidmansuri.foodrecipe.ViewModel.MealDetailFactory
import com.zaidmansuri.foodrecipe.ViewModel.MealDetailViewModel
import com.zaidmansuri.foodrecipe.databinding.ActivityMealDetailBinding
import com.zaidmansuri.foodrecipe.db.MealDataBase
import kotlinx.coroutines.launch

class MealDetail : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var binding: ActivityMealDetailBinding
    private lateinit var viewModel: MealDetailViewModel
    private  var meal: Meal?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mealDataBase:MealDataBase = MealDataBase.getInstance(this@MealDetail)
        val viewModelFactory = MealDetailFactory(mealDataBase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MealDetailViewModel::class.java)

        intent = getIntent()
        val image = intent.getStringExtra("image")
        val id: String? = intent.getStringExtra("id")

        Glide.with(binding.mealImg).load(image).into(binding.mealImg)

        lifecycleScope.launch {
            if (id != null) {
                viewModel.getMeal(id).observe(this@MealDetail, object : Observer<Meal> {
                    override fun onChanged(t: Meal?) {
                        if (t != null) {
                            meal=t
                        }
                        binding.recipe.text = t!!.strInstructions.toString()
                    }

                })
            }
        }

        binding.favBtn.setOnClickListener(){
            meal?.let {
                viewModel.insertMeal(it)
                Toast.makeText(this,"Saved Meal",Toast.LENGTH_SHORT).show()
            }
    }
}
}