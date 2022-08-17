package com.ibra.dev.warofsuits.home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ibra.dev.warofsuits.home.data.models.Card

@Dao
interface CardDao {

    @Insert
    suspend fun insertCard(card: Card)

    @Query("SELECT * FROM card")
    suspend fun getAllCard(): List<Card>

}