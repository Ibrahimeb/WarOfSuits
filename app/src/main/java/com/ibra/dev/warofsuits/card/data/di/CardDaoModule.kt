package com.ibra.dev.warofsuits.card.data.di

import com.ibra.dev.warofsuits.base.database.data.AppDataBase
import com.ibra.dev.warofsuits.card.data.dao.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn (ViewModelComponent::class)
class CardDaoModule {

    @Provides
    fun providerCardDao(database: AppDataBase): CardDao {
        return database.cardDao()
    }
}