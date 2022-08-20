package com.ibra.dev.warofsuits.game.domain.usecase

import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.game.data.models.Player
import com.ibra.dev.warofsuits.game.presentation.usecase.ICreatePlayerUseCase
import javax.inject.Inject

class CreatePlayerUseCase @Inject constructor() : ICreatePlayerUseCase {
    override fun invoke(name: String, deckList: List<Card>): Player {
        return Player(name = name, deck = deckList.toMutableList(), discardDeck = mutableListOf())
    }
}