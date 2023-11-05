package com.example.foodtestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodtestapp.data.dao.FoodDao
import com.example.foodtestapp.data.entity.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): FoodDao
}