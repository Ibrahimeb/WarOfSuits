package com.ibra.dev.warofsuits.game.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibra.dev.warofsuits.databinding.FragmentGameBinding
import com.ibra.dev.warofsuits.game.presentation.ui.adapter.DeckAdapter
import com.ibra.dev.warofsuits.game.presentation.viewmodels.GameViewModel
import com.ibra.dev.warofsuits.home.data.models.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private val viewModel: GameViewModel by viewModels()

    private lateinit var deckPlayerOne: DeckAdapter

    private lateinit var deckPlayerTwo: DeckAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = deckPlayerOne
                }
            } else {
                deckPlayerTwo = DeckAdapter(deckPlayer)
                rvGamePagePlayerTwo.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = deckPlayerTwo
                }
            }
        }
    }
}