package com.ibra.dev.warofsuits.game.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibra.dev.warofsuits.game.presentation.usecase.IDealerUseCase
import com.ibra.dev.warofsuits.card.data.models.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val dealerUseCase: IDealerUseCase
) : ViewModel() {

    private val _deckLiveData = MutableLiveData<Pair<List<Card>, List<Card>>>()
    val deckLiveData: LiveData<Pair<List<Card>, List<Card>>> get() = _deckLiveData

    fun getDeckToPlayers() {
        viewModelScope.launch {
            _deckLiveData.postValue(dealerUseCase.invoke())
        }
    }

}