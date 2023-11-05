package com.example.foodtestapp.view.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodtestapp.R
import com.example.foodtestapp.data.base.ResultResponse
import com.example.foodtestapp.data.entity.Banner
import com.example.foodtestapp.databinding.FragmentHomeBinding
import com.example.foodtestapp.view.rv_adapters.BannerAdapter
import com.example.foodtestapp.view.rv_adapters.MealAdapter
import com.example.foodtestapp.view.rv_decorators.TopSpacingItemDecoration
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val bannerAdapter = BannerAdapter()
    private val mealAdapter = MealAdapter()
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMealsAdapter()
        getMeals()
        setUpBannerAdapter()
        setCategories()
    }

    private fun setUpBannerAdapter() {
        binding.banners.apply {
            bannerAdapter.submitList(bannerDataBase)
            bannerAdapter.setHasStableIds(true)
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
            setHasFixedSize(true)
            setItemViewCacheSize(4)
        }
    }

    private fun setUpMealsAdapter() {
        binding.meals.apply {
            adapter = mealAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun setCategories() {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                binding.categories.apply {
                    categoriesDatabase.forEach{
                        addChipToGroup(it)
                    }
                }
            }
        }
    }

    private fun getMeals() {
        lifecycleScope.launch {
            viewModel.getDataFromWeb().collect { result ->
                when (result) {
                    is ResultResponse.Loading -> {
                        // Show loading state
                    }
                    is ResultResponse.Success -> {
                        val meals = result.data.meals
                        if (meals.isNotEmpty()) {
                            mealAdapter.submitList(meals)
                            withContext(Dispatchers.IO) {
                                viewModel.putDataInDB(meals)
                            }
                        }
                    }
                    is ResultResponse.Error -> {
                        val errorMessage = result.message
                        viewModel.getDataFromDB().collect{db_meals ->
                            if (db_meals.isNotEmpty())
                                mealAdapter.submitList(db_meals)
                            else
                                Toast.makeText(requireContext(),errorMessage,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun addChipToGroup(chipText: String) {
        val chip = layoutInflater.inflate(R.layout.category_item, binding.categories, false) as Chip
        chip.text = chipText
        chip.isClickable = true
        chip.isCheckable = true
        if (chipText == "Пицца")
            chip.isChecked = true
        binding.categories.addView(chip)
    }

    companion object {
        val bannerDataBase = listOf(
            Banner(1L, R.drawable.banner1),
            Banner(2L, R.drawable.banner2),
            Banner(3L, R.drawable.banner3),
            Banner(4L, R.drawable.banner2),
            Banner(5L, R.drawable.banner1),
            Banner(6L, R.drawable.banner3)
        )

        val categoriesDatabase = listOf("Пицца","Комбо","Десерты","Напитки", "Акции" ,"Бургеры")
    }
}