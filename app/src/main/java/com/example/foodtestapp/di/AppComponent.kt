package com.example.foodtestapp.di

import android.content.Context
import com.example.foodtestapp.di.modules.DatabaseModule
import com.example.foodtestapp.di.modules.DomainModule
import com.example.foodtestapp.di.modules.RemoteModule
import com.example.foodtestapp.view.fragments.home_fragment.HomeFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class,DomainModule::class,RemoteModule::class])
interface AppComponent {
   fun inject(viewModel: HomeFragmentViewModel)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): AppComponent
    }
}