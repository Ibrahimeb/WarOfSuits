package com.ibra.dev.warofsuits.game.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ibra.dev.warofsuits.R
import com.ibra.dev.warofsuits.databinding.FragmentCongratsBinding

class CongratsFragment : Fragment() {

    private lateinit var binding: FragmentCongratsBinding

    private val args by navArgs<CongratsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCongratsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvWinnerName.text =
                String.format(getString(R.string.congrats_winner, args.winnerName))
            btnNewGame.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}