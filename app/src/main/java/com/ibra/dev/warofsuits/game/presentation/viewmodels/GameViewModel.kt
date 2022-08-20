package com.ibra.dev.warofsuits.game.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.card.data.models.Suits
import com.ibra.dev.warofsuits.game.data.models.Player
import com.ibra.dev.warofsuits.game.domain.usecase.CreatePlayerUseCase
import com.ibra.dev.warofsuits.game.domain.usecase.JudgeUseCase
import com.ibra.dev.warofsuits.game.presentation.ui.contract.GameEvents
import com.ibra.dev.warofsuits.game.presentation.usecase.IDealerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val dealerUseCase: IDealerUseCase,
    private val judgeUseCase: JudgeUseCase,
    private val createPlayerUseCase: CreatePlayerUseCase
) : ViewModel() {

    private val _deckLiveData = MutableLiveData<Pair<List<Card>, List<Card>>>()
    val deckLiveData: LiveData<Pair<List<Card>, List<Card>>> get() = _deckLiveData

    private val _gameEventLiveData = MutableLiveData<GameEvents>()
    val gameEventLiveData: LiveData<GameEvents> get() = _gameEventLiveData

    private lateinit var playerOne: Player

    private lateinit var playerTwo: Player

    private lateinit var lastCardPlayerOneSelected: Card
    private lateinit var lastCardPlayerTwoSelected: Card


    private var suitsRule: List<Suits> = Suits.values().toList().shuffled()

    fun getDeckToPlayers() {
        viewModelScope.launch {
            _deckLiveData.postValue(dealerUseCase.invoke())
        }
    }

    fun updateSizeDecks() {
        deckSizeEvent()
        discardSizeEvent()
        layDownTimeEvent()
    }

    private fun deckSizeEvent() {
        _gameEventLiveData.value = GameEvents.UpdatePlayersSizeDecks(
            sizeDeckPlayerOne = playerOne.deck.size,
            sizeDeckPlayerTwo = playerTwo.deck.size
        )

    }

    private fun discardSizeEvent() {
        _gameEventLiveData.value = GameEvents.UpdatePlayersSizeDiscardDecks(
            sizeDiscardPlayerOne = playerOne.discardDeck.size,
            sizeDiscardPlayerTwo = playerTwo.discardDeck.size
        )
    }

    fun createPlayersOne(name: String, deck: List<Card>) {
        playerOne = createPlayerUseCase.invoke(name = name, deckList = deck)
    }

    fun createPlayersTwo(name: String, deck: List<Card>) {
        playerTwo = createPlayerUseCase.invoke(name = name, deckList = deck)
    }

    private fun validateMatch(playerOneCard: Card, playerTwoCard: Card) {
        val event = judgeUseCase.invoke(playerOneCard, playerTwoCard, suitsRule)
        _gameEventLiveData.value = event
    }

    fun layDownCard() {
        if (isEmptyPlayerDeck().not()) {
            lastCardPlayerOneSelected = playerOne.deck.removeFirst()
            lastCardPlayerTwoSelected = playerTwo.deck.removeFirst()
            drawEvent()
        } else {
            validateTimeEvent()
        }
    }

    fun validateCards() {
        validateMatch(
            playerOneCard = lastCardPlayerOneSelected,
            playerTwoCard = lastCardPlayerTwoSelected
        )
        layDownTimeEvent()
    }

    private fun drawEvent() {
        _gameEventLiveData.value = GameEvents.DrawMatch(
            cardPlayerOne = lastCardPlayerOneSelected,
            cardPlayerTwo = lastCardPlayerTwoSelected
        )
        deckSizeEvent()
        validateTimeEvent()
    }

    fun updateDiscardDeckPlayerOne() {
        playerOne.apply {
            discardDeck.add(lastCardPlayerOneSelected)
            discardDeck.add(lastCardPlayerTwoSelected)
        }
        discardSizeEvent()
    }

    fun updateDiscardDeckPlayerTwo() {
        playerTwo.apply {
            discardDeck.add(lastCardPlayerOneSelected)
            discardDeck.add(lastCardPlayerTwoSelected)
        }
        discardSizeEvent()
    }

    private fun validateTimeEvent() {
        _gameEventLiveData.value = GameEvents.ValidateTime
    }

    private fun layDownTimeEvent() {
        _gameEventLiveData.value = GameEvents.LayDownTime
    }

    fun isGameOver() {
        if (isEmptyPlayerDeck()) {
            _gameEventLiveData.postValue(
                GameEvents.GameOver(
                    if (playerOne.discardDeck.size > playerTwo.discardDeck.size) {
                        playerOne.name
                    } else {
                        playerTwo.name
                    }
                )
            )
        }
    }

    private fun isEmptyPlayerDeck() = playerOne.deck.isEmpty() || playerTwo.deck.isEmpty()
}