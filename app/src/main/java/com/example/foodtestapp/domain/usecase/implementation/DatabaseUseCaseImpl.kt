package com.example.foodtestapp.domain.usecase.implementation

import com.example.foodtestapp.data.entity.Meal
import com.example.foodtestapp.domain.repositroies.DatabaseRepository
import com.example.foodtestapp.domain.repositroies.implementation.DatabaseRepositoryImpl
import com.example.foodtestapp.domain.usecase.DatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DatabaseUseCaseImpl(private val repository: DatabaseRepository): DatabaseUseCase {
    override fun getDataFromDB(): Flow<List<Meal>> = flow {
        val result = repository.getMealsFromDb()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun putDataInDB(meals: List<Meal>) {
        repository.putMealsInDb(meals)
    }
}