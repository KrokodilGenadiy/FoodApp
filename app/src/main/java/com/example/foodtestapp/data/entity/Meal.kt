package com.example.foodtestapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "meals", indices = [Index(value = ["strMeal"], unique = true)])
data class Meal(
    @PrimaryKey
    val idMeal: String,
    val strArea: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String
): Parcelable