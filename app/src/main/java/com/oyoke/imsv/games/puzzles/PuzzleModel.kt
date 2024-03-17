package com.oyoke.imsv.games.puzzles

data class PuzzleModel(
    val id: Int,
    val title: String,
    val question: String,
    val image: Int,
    var answer: String,
    var isSolved: Boolean = false,
    var isClickable: Boolean = false
)