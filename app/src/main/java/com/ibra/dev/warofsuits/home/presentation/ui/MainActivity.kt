package com.ibra.dev.warofsuits.home.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ibra.dev.warofsuits.R
import com.ibra.dev.warofsuits.base.utils.simpleNameClass
import com.ibra.dev.warofsuits.game.presentation.viewmodels.GameViewModel
import com.ibra.dev.warofsuits.home.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameViewModel.deckLiveData.observe(this) {
            Log.i(simpleNameClass(), "onCreate: deck size = ${it.first.size} ")
            it.first.forEach { card ->
                Log.i(simpleNameClass(), "onCreate: card player one $card")
            }

            Log.i(simpleNameClass(), "onCreate: deck size = ${it.second.size} ")
            it.second.forEach { card ->
                Log.i(simpleNameClass(), "onCreate: card player two $card")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.createDeck()
        gameViewModel.getDeckToPlayers()
    }
}