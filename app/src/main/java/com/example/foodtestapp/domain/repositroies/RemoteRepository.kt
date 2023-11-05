package com.example.foodtestapp.domain.repositroies

import com.example.foodtestapp.data.base.ResultResponse
import com.example.foodtestapp.data.entity.Meals

interface RemoteRepository {
    suspend fun getMeals(): ResultResponse<Meals>
}