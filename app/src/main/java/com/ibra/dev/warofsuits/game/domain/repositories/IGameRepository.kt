package com.ibra.dev.warofsuits.game.domain.repositories

import com.ibra.dev.warofsuits.home.data.models.Card

interface IGameRepository {

   suspend fun getDeck(): List<Card>
}