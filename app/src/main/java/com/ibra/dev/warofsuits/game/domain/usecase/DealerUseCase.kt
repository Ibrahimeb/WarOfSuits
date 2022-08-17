package com.ibra.dev.warofsuits.game.domain.usecase

import com.ibra.dev.warofsuits.game.domain.repositories.IGameRepository
import com.ibra.dev.warofsuits.game.presentation.usecase.IDealerUseCase
import com.ibra.dev.warofsuits.home.data.models.Card
import javax.inject.Inject

class DealerUseCase @Inject constructor(
    private val gameRepository: IGameRepository
) : IDealerUseCase {
    override suspend fun invoke(): Pair<List<Card>, List<Card>> {
        val splitDeck: Pair<MutableList<Card>, MutableList<Card>> = Pair(mutableListOf(), mutableListOf())
        gameRepository.getDeck().shuffled().forEachIndexed { index, card ->
            if (index < gameRepository.getDeck().size.div(2)) {
                splitDeck.first.add(card)
            } else {
                splitDeck.second.add(card)
            }
        }
        splitDeck.first.toList()
        splitDeck.second.toList()
        return splitDeck
    }
}