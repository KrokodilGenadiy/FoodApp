package com.example.foodtestapp.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import com.example.foodtestapp.data.entity.Meal
import com.example.foodtestapp.domain.usecase.DatabaseUseCase
import com.example.foodtestapp.domain.usecase.RemoteUseCase
import com.example.foodtestapp.domain.usecase.implementation.DatabaseUseCaseImpl
import com.example.foodtestapp.domain.usecase.implementation.RemoteUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val remoteUseCase: RemoteUseCase, private val databaseUseCase: DatabaseUseCase): ViewModel() {
    fun getDataFromWeb() = remoteUseCase.getDataFromWeb()
    fun getDataFromDB() = databaseUseCase.getDataFromDB()
    suspend fun putDataInDB(meals: List<Meal>) {
        databaseUseCase.putDataInDB(meals)
    }

}