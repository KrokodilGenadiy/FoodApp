package com.example.foodtestapp.di.modules

import com.example.foodtestapp.data.FoodApi
import com.example.foodtestapp.data.dao.FoodDao
import com.example.foodtestapp.domain.repositroies.DatabaseRepository
import com.example.foodtestapp.domain.repositroies.RemoteRepository
import com.example.foodtestapp.domain.repositroies.implementation.DatabaseRepositoryImpl
import com.example.foodtestapp.domain.repositroies.implementation.RemoteRepositoryImpl
import com.example.foodtestapp.domain.usecase.DatabaseUseCase
import com.example.foodtestapp.domain.usecase.RemoteUseCase
import com.example.foodtestapp.domain.usecase.implementation.DatabaseUseCaseImpl
import com.example.foodtestapp.domain.usecase.implementation.RemoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(foodApi: FoodApi): RemoteRepository = RemoteRepositoryImpl(foodApi)

    @Singleton
    @Provides
    fun provideDatabaseRepository(foodDao: FoodDao): DatabaseRepository = DatabaseRepositoryImpl(foodDao)

    @Singleton
    @Provides
    fun provideRemoteUseCase(repository: RemoteRepository): RemoteUseCase = RemoteUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideDatabaseUseCase(repository: DatabaseRepository): DatabaseUseCase = DatabaseUseCaseImpl(repository)
}