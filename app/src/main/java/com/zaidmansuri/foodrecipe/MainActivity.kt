package com.zaidmansuri.foodrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bnv=findViewById<BottomNavigationView>(R.id.bnv)
        val navController=Navigation.findNavController(this,R.id.fragment)

        NavigationUI.setupWithNavController(bnv,navController)
    }
}