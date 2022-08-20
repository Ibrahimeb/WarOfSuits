package com.ibra.dev.warofsuits.base.utils

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.ibra.dev.warofsuits.R
import com.ibra.dev.warofsuits.card.data.models.Suits


inline fun <reified T> T.simpleNameClass(): String = T::class.java.simpleName

fun Int.formatNumberCard(): String = when (this) {
    1 -> "A"
    11 -> "J"
    12 -> "Q"
    13 -> "K"
    else -> this.toString()
}

fun Suits.getSuitsIcon(context: Context) = when (this) {
    Suits.HEARTS -> AppCompatResources.getDrawable(context, R.drawable.ic_heart)
    Suits.CLUBS -> AppCompatResources.getDrawable(context, R.drawable.ic_clubs)
    Suits.DIAMONDS -> AppCompatResources.getDrawable(context, R.drawable.ic_diamonds)
    Suits.SPADES -> AppCompatResources.getDrawable(context, R.drawable.ic_spades)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enabled() {
    this.isEnabled = true
}
