package com.ibra.dev.warofsuits.base.database.module

import android.content.Context
import androidx.room.Room
import com.ibra.dev.warofsuits.base.database.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private companion object {
        private const val DATABASE_NAME = "app.store.database"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}