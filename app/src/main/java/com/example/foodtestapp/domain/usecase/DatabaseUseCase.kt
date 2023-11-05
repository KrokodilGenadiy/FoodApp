package com.example.foodtestapp.domain.usecase

import com.example.foodtestapp.data.entity.Meal
import kotlinx.coroutines.flow.Flow

interface DatabaseUseCase {
    fun getDataFromDB(): Flow<List<Meal>>
    suspend fun putDataInDB(meals: List<Meal>)
}