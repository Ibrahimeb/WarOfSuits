package com.ibra.dev.warofsuits.game.presentation.usecase

import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.game.data.models.Player

interface ICreatePlayerUseCase {

    fun invoke(name:String,deckList: List<Card>): Player
}