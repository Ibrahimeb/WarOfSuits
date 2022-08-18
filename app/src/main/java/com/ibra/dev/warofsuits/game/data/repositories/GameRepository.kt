package com.ibra.dev.warofsuits.game.data.repositories

import com.ibra.dev.warofsuits.game.domain.repositories.IGameRepository
import com.ibra.dev.warofsuits.card.data.dao.CardDao
import com.ibra.dev.warofsuits.card.data.models.Card
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val cardDao: CardDao
):IGameRepository {

    override suspend fun getDeck(): List<Card> {
        return cardDao.getAllCard()
    }


}