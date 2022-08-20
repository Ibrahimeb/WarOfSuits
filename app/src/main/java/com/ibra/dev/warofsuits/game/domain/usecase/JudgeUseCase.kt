package com.ibra.dev.warofsuits.game.domain.usecase

import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.card.data.models.Suits
import com.ibra.dev.warofsuits.game.presentation.ui.contract.GameEvents
import com.ibra.dev.warofsuits.game.presentation.usecase.IJudgeUseCase
import javax.inject.Inject

class JudgeUseCase @Inject constructor() : IJudgeUseCase {
    override fun invoke(
        cardPlayerOne: Card,
        cardPlayerTwo: Card,
        suitsRule: List<Suits>
    ): GameEvents {
        return validateMatch(cardPlayerOne, cardPlayerTwo, suitsRule)
    }

    private fun validateMatch(
        cardPlayerOne: Card,
        cardPlayerTwo: Card,
        suitsRule: List<Suits>
    ): GameEvents {
        return when {
            cardPlayerOne.number == cardPlayerTwo.number -> unTied(
                cardPlayerOne.suits,
                cardPlayerTwo.suits,
                suitsRule
            )
            cardPlayerOne.number == 1 -> GameEvents.PlayerOneWon
            cardPlayerTwo.number == 1 -> GameEvents.PlayerTwoWon
            else -> validateWinner(cardPlayerOne.number, cardPlayerTwo.number)
        }
    }

    private fun validateWinner(cardNumberPlayerOne: Int, cardNumberPlayerTwo: Int): GameEvents {
        return if (cardNumberPlayerOne > cardNumberPlayerTwo) GameEvents.PlayerOneWon else GameEvents.PlayerTwoWon
    }

    private fun unTied(suits: Suits, suits2: Suits, suitsRule: List<Suits>): GameEvents {
        val indexPlayerOne = suitsRule.indexOf(suits)
        val indexPlayerTwo = suitsRule.indexOf(suits2)
        return if (indexPlayerOne > indexPlayerTwo) GameEvents.PlayerOneWon else GameEvents.PlayerTwoWon
    }
}