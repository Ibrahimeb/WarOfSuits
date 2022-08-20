package com.ibra.dev.warofsuits.game.di

import com.ibra.dev.warofsuits.game.domain.usecase.CreatePlayerUseCase
import com.ibra.dev.warofsuits.game.presentation.usecase.ICreatePlayerUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CreatePlayerUseCaseModule {

    @Binds
    abstract fun bindsCreatePlayerUseCase(useCase: CreatePlayerUseCase): ICreatePlayerUseCase
}