package com.ibra.dev.warofsuits.game.presentation.ui.contract

import com.ibra.dev.warofsuits.card.data.models.Card

sealed class GameEvents {
    object PlayerOneWon : GameEvents()
    object PlayerTwoWon : GameEvents()
    class DrawMatch(val cardPlayerOne: Card, val cardPlayerTwo: Card) : GameEvents()
    class UpdatePlayersSizeDecks(val sizeDeckPlayerOne: Int, val sizeDeckPlayerTwo: Int) :
        GameEvents()

    class UpdatePlayersSizeDiscardDecks(
        val sizeDiscardPlayerOne: Int,
        val sizeDiscardPlayerTwo: Int
    ) : GameEvents()

    class GameOver(val winnerPlayer: String) : GameEvents()

    object LayDownTime : GameEvents()
    object ValidateTime : GameEvents()
}
