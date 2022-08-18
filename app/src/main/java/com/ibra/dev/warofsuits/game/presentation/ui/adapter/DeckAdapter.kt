package com.ibra.dev.warofsuits.game.presentation.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibra.dev.warofsuits.game.presentation.ui.viewholder.CardViewHolder
import com.ibra.dev.warofsuits.card.data.models.Card

class DeckAdapter(private val playerDeck: List<Card>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.render(playerDeck[position])
    }

    override fun getItemCount(): Int {
        return playerDeck.size
    }
}