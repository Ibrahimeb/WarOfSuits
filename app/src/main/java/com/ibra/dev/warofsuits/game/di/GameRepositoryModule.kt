package com.ibra.dev.warofsuits.game.di

import com.ibra.dev.warofsuits.game.data.repositories.GameRepository
import com.ibra.dev.warofsuits.game.domain.repositories.IGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GameRepositoryModule {

    @Binds
    abstract fun bindsGameRepository(repository: GameRepository): IGameRepository
}