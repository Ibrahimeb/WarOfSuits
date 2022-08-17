package com.ibra.dev.warofsuits.home.di

import com.ibra.dev.warofsuits.home.domain.usecase.CreateDeckUseCase
import com.ibra.dev.warofsuits.home.presentation.usecase.ICreateDeckUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CreateDeckUseCaseModule {

    @Binds
    abstract fun bindsUseCase(useCase: CreateDeckUseCase): ICreateDeckUseCase
}