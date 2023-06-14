package com.example.calculator.di

import android.content.Context
import com.example.calculator.CalculatorApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): CalculatorApplication = context as CalculatorApplication
}