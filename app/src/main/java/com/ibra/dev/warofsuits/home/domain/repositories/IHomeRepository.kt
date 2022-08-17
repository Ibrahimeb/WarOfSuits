package com.ibra.dev.warofsuits.home.domain.repositories

import com.ibra.dev.warofsuits.home.data.models.Card

interface IHomeRepository {

    suspend fun saveCards(deck:List<Card>)
    suspend fun cardsNotExist(): Boolean
}