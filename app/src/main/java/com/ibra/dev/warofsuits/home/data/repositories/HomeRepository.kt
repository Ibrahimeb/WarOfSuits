package com.ibra.dev.warofsuits.home.data.repositories

import android.util.Log
import com.ibra.dev.warofsuits.base.utils.simpleNameClass
import com.ibra.dev.warofsuits.home.data.dao.CardDao
import com.ibra.dev.warofsuits.home.data.models.Card
import com.ibra.dev.warofsuits.home.domain.repositories.IHomeRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val cardDao: CardDao
) : IHomeRepository {
    override suspend fun saveCards(deck: List<Card>) {
        deck.forEach { card ->
            cardDao.insertCard(card)
            Log.i(simpleNameClass(), "saveCards: $card ")
        }
    }

    override suspend fun cardsNotExist(): Boolean {
        Log.i(simpleNameClass(), "cardsNotExist: ${cardDao.getAllCard().size}")
        return cardDao.getAllCard().let {
            it.isEmpty().or(it.size < 52)
        }
    }
}