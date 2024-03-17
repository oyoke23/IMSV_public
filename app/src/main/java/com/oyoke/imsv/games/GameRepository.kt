package com.oyoke.imsv.games

import com.oyoke.imsv.R

object GameRepository {
    val games = listOf(
        Game("Quiz", R.id.quizMenuFragment, R.drawable.banner_quiz),
        Game(
            "Memory Card",
            R.id.cardMenuFragment,
            R.drawable.banner_memory_card
        ),
        Game("Puzzles", R.id.puzzleMenuFragment, R.drawable.banner_puzzles),
        Game(
            "Would He Rather",
            R.id.wouldHeRatherMenuFragment,
            R.drawable.banner_would_he_rather
        ),
    )
}