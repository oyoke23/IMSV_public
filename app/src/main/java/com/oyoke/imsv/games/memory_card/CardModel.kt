package com.oyoke.imsv.games.memory_card


data class CardModel(val id: Int, val pairId: Int, val image: Int, var isFlipped: Boolean = false)
