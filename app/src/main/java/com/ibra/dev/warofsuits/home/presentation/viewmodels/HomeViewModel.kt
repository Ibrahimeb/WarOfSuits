package com.ibra.dev.warofsuits.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibra.dev.warofsuits.home.domain.usecase.CreateDeckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: CreateDeckUseCase
) : ViewModel() {

    fun createDeck(){
        viewModelScope.launch {
            useCase.invoke()
        }
    }

}