package com.example.foodtestapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.foodtestapp.data.dao.FoodDao
import com.example.foodtestapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideFoodDao(database: AppDatabase): FoodDao {
        return database.mealDao()
    }
}