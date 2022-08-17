package com.ibra.dev.warofsuits.home.di

import com.ibra.dev.warofsuits.home.data.repositories.HomeRepository
import com.ibra.dev.warofsuits.home.domain.repositories.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun bindRepository(repository: HomeRepository): IHomeRepository
}