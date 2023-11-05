package com.example.foodtestapp.domain.repositroies.implementation

import com.example.foodtestapp.data.base.ResultResponse
import com.example.foodtestapp.data.FoodApi
import com.example.foodtestapp.data.entity.Meals
import com.example.foodtestapp.domain.repositroies.RemoteRepository
import retrofit2.Response

class RemoteRepositoryImpl (private val retrofitService: FoodApi): RemoteRepository {
    override suspend fun getMeals(): ResultResponse<Meals> {
        return try {
            val response: Response<Meals> = retrofitService.getMeals()
            if (response.isSuccessful) {
                ResultResponse.Success(response.body()!!)
            } else {
                ResultResponse.Error(response.message())
            }
        } catch (e: Exception) {
            ResultResponse.Error(e.message ?: "An error occurred")
        }
    }
}
