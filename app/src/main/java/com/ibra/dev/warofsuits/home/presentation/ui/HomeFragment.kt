package com.ibra.dev.warofsuits.home.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ibra.dev.warofsuits.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenTextOnChange()
        with(binding) {
            btnHomeGoToPlay.setOnClickListener {
                when {
                    tfHomePageNamePlayerOne.text?.isEmpty() == true -> tflHomePageNamePlayerOne.error =
                        "name is required"
                    tfHomePageNamePlayerTwo.text?.isEmpty() == true -> tflHomePageNamePlayerTwo.error =
                        "name is required"
                    else -> findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToGameFragment(
                            namePlayerOne = tfHomePageNamePlayerOne.text.toString(),
                            namePlayerTwo = tfHomePageNamePlayerTwo.text.toString()
                        )
                    )
                }
            }
        }
    }

    private fun listenTextOnChange(){
        with(binding){
            tfHomePageNamePlayerOne.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty().not()) tflHomePageNamePlayerOne.error = null
            }

            tfHomePageNamePlayerTwo.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty().not()) tflHomePageNamePlayerTwo.error = null
            }
        }
    }
}