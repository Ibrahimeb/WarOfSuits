package com.ibra.dev.warofsuits.game.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ibra.dev.warofsuits.base.utils.disable
import com.ibra.dev.warofsuits.base.utils.enabled
import com.ibra.dev.warofsuits.base.utils.formatNumberCard
import com.ibra.dev.warofsuits.base.utils.getSuitsIcon
import com.ibra.dev.warofsuits.base.utils.hide
import com.ibra.dev.warofsuits.base.utils.show
import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.databinding.FragmentGameBinding
import com.ibra.dev.warofsuits.game.presentation.ui.contract.GameEvents
import com.ibra.dev.warofsuits.game.presentation.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private val viewModel: GameViewModel by viewModels()

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

    private fun setupViews() {
        with(binding) {
            tvGamePageNamePlayerOne.text = args.namePlayerOne
            tvGamePageNamePlayerTwo.text = args.namePlayerTwo
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetGame()
        setupViews()
        onClickListener()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        viewModel.apply {
            deckLiveData.observe(viewLifecycleOwner) {
                viewModel.createPlayersOne(args.namePlayerOne, it.first)
                viewModel.createPlayersTwo(args.namePlayerTwo, it.second)
                updateSizeDecks()
            }
            gameEventLiveData.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is GameEvents.DrawMatch -> drawFightOrClear(
                        event.cardPlayerOne,
                        event.cardPlayerTwo
                    )
                    GameEvents.PlayerOneWon -> handlerPostValidate(event)
                    GameEvents.PlayerTwoWon -> handlerPostValidate(event)
                    is GameEvents.UpdatePlayersSizeDecks -> setSizeInDeck(
                        sizeDeckPlayerOne = event.sizeDeckPlayerOne,
                        sizeDeckPlayerTwo = event.sizeDeckPlayerTwo
                    )
                    is GameEvents.UpdatePlayersSizeDiscardDecks -> setSizeInDiscardDeck(
                        sizeDiscardPlayerOne = event.sizeDiscardPlayerOne,
                        sizeDiscardPlayerTwo = event.sizeDiscardPlayerTwo
                    )
                    GameEvents.LayDownTime -> {
                        binding.apply {
                            btnFightGame.enabled()
                            btnValidateGame.disable()
                        }
                    }
                    GameEvents.ValidateTime -> {
                        binding.apply {
                            btnFightGame.disable()
                            btnValidateGame.enabled()
                        }
                    }
                    is GameEvents.GameOver -> {
                        binding.apply {
                            btnFightGame.disable()
                            btnValidateGame.disable()
                        }
                        findNavController().navigate(
                            GameFragmentDirections.actionGameFragmentToCongratsFragment(
                                event.winnerPlayer
                            )
                        )
                    }
                }

            }
        }
    }

    private fun handlerPostValidate(events: GameEvents) {
        viewModel.isGameOver()
        if (events is GameEvents.PlayerOneWon) {
            viewModel.updateDiscardDeckPlayerOne()
            binding.animationWinPlayerOne.apply {
                show()
                playAnimation()
            }
        } else {
            viewModel.updateDiscardDeckPlayerTwo()
            binding.animationWinPlayerTwo.apply {
                show()
                playAnimation()
            }
        }
    }

    private fun onClickListener() {
        with(binding) {
            btnFightGame.setOnClickListener {
                hideWinnerAnimations()
                viewModel.layDownCard()
            }

            btnValidateGame.setOnClickListener {
                viewModel.validateCards()
            }

            btnResetGame.setOnClickListener {
                hideWinnerAnimations()
                resetGame()
            }
        }
    }

    private fun hideWinnerAnimations() {
        with(binding) {
            animationWinPlayerOne.hide()
            animationWinPlayerTwo.hide()
        }
    }

    private fun setSizeInDeck(sizeDeckPlayerOne: Int, sizeDeckPlayerTwo: Int) {
        with(binding) {
            includedPlayerOneDeckIndicator.tvGamePageSizeDeck.text = sizeDeckPlayerOne.toString()
            includedPlayerTwoDeckIndicator.tvGamePageSizeDeck.text = sizeDeckPlayerTwo.toString()
        }
    }

    private fun setSizeInDiscardDeck(sizeDiscardPlayerOne: Int, sizeDiscardPlayerTwo: Int) {
        with(binding) {
            includedPlayerOneDiscardIndicator.tvGamePageSizeDeck.text =
                sizeDiscardPlayerOne.toString()
            includedPlayerTwoDiscardIndicator.tvGamePageSizeDeck.text =
                sizeDiscardPlayerTwo.toString()
        }
    }

    private fun resetGame() {
        viewModel.getDeckToPlayers()
        drawFightOrClear(clear = true)
        hideWinnerAnimations()
    }

    private fun drawFightOrClear(
        cardPlayerOne: Card? = null,
        cardPlayerTwo: Card? = null,
        clear: Boolean = false
    ) {
        with(binding) {
            includedCardPlayerOne.apply {
                tvCardNumber.text = if (clear) "" else cardPlayerOne?.number?.formatNumberCard()
                ivSuitsIcon.setImageDrawable(
                    if (clear) null else cardPlayerOne?.suits?.getSuitsIcon(
                        requireContext()
                    )
                )
            }

            includedCardPlayerTwo.apply {
                tvCardNumber.text = cardPlayerTwo?.number?.formatNumberCard()
                ivSuitsIcon.setImageDrawable(cardPlayerTwo?.suits?.getSuitsIcon(requireContext()))
            }
        }
    }
}