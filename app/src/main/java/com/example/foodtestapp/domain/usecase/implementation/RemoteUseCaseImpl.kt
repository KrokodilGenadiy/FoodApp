package com.example.foodtestapp.domain.usecase.implementation

import com.example.foodtestapp.data.base.ResultResponse
import com.example.foodtestapp.data.entity.Meals
import com.example.foodtestapp.domain.repositroies.RemoteRepository
import com.example.foodtestapp.domain.repositroies.implementation.RemoteRepositoryImpl
import com.example.foodtestapp.domain.usecase.RemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteUseCaseImpl(private val repository: RemoteRepository): RemoteUseCase {
    override fun getDataFromWeb(): Flow<ResultResponse<Meals>> = flow {
        emit(ResultResponse.Loading)
        val result = repository.getMeals()
        emit(result)
    }.flowOn(Dispatchers.IO)
}