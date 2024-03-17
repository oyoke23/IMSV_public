package com.oyoke.imsv.games.puzzles

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oyoke.imsv.R

object PuzzleRepository {
    private const val PREFS_NAME = "PuzzlesPrefs"

    fun markPuzzleAsSolved(context: Context, puzzleId: Int) {
        val puzzles = getPuzzles(context)
        val solvedPuzzle = puzzles.find { it.id == puzzleId }
        solvedPuzzle?.isSolved = true
        savePuzzles(context, puzzles)
    }

    fun getPuzzles(context: Context): List<PuzzleModel> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString("puzzles", null)
        return if (json != null) {
            gson.fromJson(json, object : TypeToken<List<PuzzleModel>>() {}.type)
        } else {
            getDefaultPuzzles()
        }
    }

    fun savePuzzles(context: Context, puzzles: List<PuzzleModel>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(puzzles)
        editor.putString("puzzles", json)
        editor.apply()
    }

    private fun getDefaultPuzzles(): List<PuzzleModel> {
        return listOf(
            PuzzleModel(
                0,
                "Example title 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.example,
                "1"
            ),
            PuzzleModel(
                1,
                "Example title 2",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.example,
                "2"
            ),
            PuzzleModel(
                2,
                "Example title 3",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.example,
                "3"
            ),
        )
    }
}