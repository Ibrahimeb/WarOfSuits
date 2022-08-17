package com.ibra.dev.warofsuits.game.di

import com.ibra.dev.warofsuits.game.domain.usecase.DealerUseCase
import com.ibra.dev.warofsuits.game.presentation.usecase.IDealerUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DealerUseCaseModule {

    @Binds
    abstract fun bindsDealerUseCase(useCase: DealerUseCase): IDealerUseCase
}