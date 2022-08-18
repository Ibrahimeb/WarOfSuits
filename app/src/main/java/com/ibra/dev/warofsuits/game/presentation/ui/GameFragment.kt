package com.ibra.dev.warofsuits.game.presentation.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ibra.dev.warofsuits.databinding.FragmentGameBinding
import com.ibra.dev.warofsuits.game.presentation.ui.adapter.DeckAdapter
import com.ibra.dev.warofsuits.game.presentation.viewmodels.GameViewModel
import com.ibra.dev.warofsuits.card.data.models.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private val viewModel: GameViewModel by viewModels()

    private lateinit var deckPlayerOne: DeckAdapter

    private lateinit var deckPlayerTwo: DeckAdapter

    private val args by navArgs<GameFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDeckToPlayers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    private fun setName() {
        with(binding) {
            tvGamePageNamePlayerTwo?.text = args.namePlayerTwo
            tvGamePageNamePlayerOne?.text = args.namePlayerOne
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setName()
        viewModel.deckLiveData.observe(viewLifecycleOwner) {
            setupAdapter(true, it.first)
            setupAdapter(false, it.second)
        }
    }

    private fun setupAdapter(setupPlayerOne: Boolean, deckPlayer: List<Card>) {
        with(binding) {
            if (setupPlayerOne) {
                deckPlayerOne = DeckAdapter(deckPlayer)
                rvGamePagePlayerOne.apply {
                    adapter = deckPlayerOne
                }
            } else {
                deckPlayerTwo = DeckAdapter(deckPlayer)
                rvGamePagePlayerTwo.apply {
                    adapter = deckPlayerTwo
                }
            }
        }
    }
}