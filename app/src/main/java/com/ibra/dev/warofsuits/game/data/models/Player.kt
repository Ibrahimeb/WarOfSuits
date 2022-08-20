package com.ibra.dev.warofsuits.game.data.models

import com.ibra.dev.warofsuits.card.data.models.Card


data class Player(
    val name: String,
    val deck: MutableList<Card>,
    val discardDeck: MutableList<Card>
)