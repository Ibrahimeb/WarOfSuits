package com.ibra.dev.warofsuits.game.presentation.usecase

import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.card.data.models.Suits
import com.ibra.dev.warofsuits.game.presentation.ui.contract.GameEvents

interface IJudgeUseCase {

    fun invoke(cardPlayerOne: Card, cardPlayerTwo: Card, suitsRule: List<Suits>): GameEvents
}