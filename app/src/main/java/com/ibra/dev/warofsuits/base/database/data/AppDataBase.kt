package com.ibra.dev.warofsuits.base.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibra.dev.warofsuits.card.data.dao.CardDao
import com.ibra.dev.warofsuits.card.data.models.Card

@Database(entities = [Card::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun cardDao(): CardDao
}