package com.zaidmansuri.foodrecipe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaidmansuri.foodrecipe.Adapter.CategoryAdapter
import com.zaidmansuri.foodrecipe.Model.Category
import com.zaidmansuri.foodrecipe.R
import com.zaidmansuri.foodrecipe.ViewModel.HomeViewModel
import com.zaidmansuri.foodrecipe.databinding.FragmentCategoryBinding
import kotlinx.coroutines.launch

class CatagoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        categoryAdapter = CategoryAdapter()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getCategory().observe(viewLifecycleOwner) { categoryList ->
                categoryAdapter.setCategoryList(categoryList = categoryList as ArrayList<Category>)
                binding.categoryFragmentRecycle.apply {
                    layoutManager = GridLayoutManager(context,3)
                    adapter = categoryAdapter
                }
            }
        }

    }

}