package com.ibra.dev.warofsuits.home.domain.usecase

import android.util.Log
import com.ibra.dev.warofsuits.base.utils.simpleNameClass
import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.card.data.models.Suits
import com.ibra.dev.warofsuits.home.domain.repositories.IHomeRepository
import com.ibra.dev.warofsuits.home.presentation.usecase.ICreateDeckUseCase
import javax.inject.Inject

class CreateDeckUseCase @Inject constructor(
    private val repository: IHomeRepository
) : ICreateDeckUseCase {
    override suspend fun invoke() {
        if (repository.cardsNotExist()) {
            createDeck()
        } else {
            Log.i(simpleNameClass(), "invoke: exist card")
        }
    }

    private suspend fun createDeck() {
        val deck = mutableListOf<Card>()
        for (i in 0..3) {
            for (j in 1..13) {
                deck.add(
                    Card(
                        number = j,
                        suits = Suits.values()[i]
                    )
                )
            }
        }
        repository.saveCards(deck.toList())
    }
}