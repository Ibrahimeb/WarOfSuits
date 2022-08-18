package com.ibra.dev.warofsuits.game.presentation.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibra.dev.warofsuits.card.data.models.Card
import com.ibra.dev.warofsuits.databinding.ViewPokerCardBinding

class CardViewHolder(private val binding: ViewPokerCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): CardViewHolder {
            val binding = ViewPokerCardBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CardViewHolder(binding)
        }
    }

    fun render(card: Card) {}
}