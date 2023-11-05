package com.example.foodtestapp.domain.repositroies.implementation

import com.example.foodtestapp.data.dao.FoodDao
import com.example.foodtestapp.data.entity.Meal
import com.example.foodtestapp.domain.repositroies.DatabaseRepository

class DatabaseRepositoryImpl(private val mealDao: FoodDao): DatabaseRepository {
    override suspend fun putMealsInDb(meals: List<Meal>) {
        mealDao.insertAll(meals)
    }

    override suspend fun getMealsFromDb() = mealDao.getMeals()
}