package com.example.foodtestapp.data

import com.example.foodtestapp.data.entity.Meal
import com.example.foodtestapp.data.entity.Meals
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("1/search.php?f=b")
    suspend fun getMeals() : Response<Meals>
}