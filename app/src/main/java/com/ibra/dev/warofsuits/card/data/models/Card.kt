package com.ibra.dev.warofsuits.card.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "suits") val suits: Suits
) {
    override fun toString(): String {
        return "{number = $number, suits = ${suits.name}}"
    }
}
