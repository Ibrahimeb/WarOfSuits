package com.ibra.dev.warofsuits.game.presentation.usecase

import com.ibra.dev.warofsuits.home.data.models.Card

interface IDealerUseCase {

   suspend fun invoke(): Pair<List<Card>, List<Card>>
}