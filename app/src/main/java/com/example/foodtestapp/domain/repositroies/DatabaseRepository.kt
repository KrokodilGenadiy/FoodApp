package com.example.foodtestapp.domain.repositroies

import com.example.foodtestapp.data.entity.Meal

interface DatabaseRepository {
    suspend fun getMealsFromDb(): List<Meal>
    suspend fun putMealsInDb(meals: List<Meal>)
}