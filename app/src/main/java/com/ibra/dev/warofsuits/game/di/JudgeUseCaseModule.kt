package com.ibra.dev.warofsuits.game.di

import com.ibra.dev.warofsuits.game.domain.usecase.JudgeUseCase
import com.ibra.dev.warofsuits.game.presentation.usecase.IJudgeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract  class JudgeUseCaseModule {

    @Binds
    abstract fun bindsJudgeUseCase(useCase:JudgeUseCase):IJudgeUseCase
}