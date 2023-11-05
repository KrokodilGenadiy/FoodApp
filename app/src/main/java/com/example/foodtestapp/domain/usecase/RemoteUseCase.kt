package com.example.foodtestapp.domain.usecase

import com.example.foodtestapp.data.base.ResultResponse
import com.example.foodtestapp.data.entity.Meals
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    fun getDataFromWeb(): Flow<ResultResponse<Meals>>
}