package com.oyoke.imsv.games.quiz

import android.util.Log

object QuizRepository {
    private var allQuestions = mutableListOf<QuizModel>()

    fun random(): QuizModel {

        val position = (allQuestions.indices).random()
        val selectedQuestion = allQuestions[position]

        allQuestions.remove(selectedQuestion)

        return selectedQuestion
    }

    fun getQuestions(difficulty: Int): Int {
        when (difficulty) {
            1 -> {
                allQuestions = quizEasy.shuffled().take(10).toMutableList()
            }

            2 -> {
                allQuestions =
                    (quizEasy.shuffled().take(10) + quizMedium.shuffled().take(5)).toMutableList()
            }

            3 -> {
                allQuestions = (quizEasy.shuffled().take(10) + quizMedium.shuffled()
                    .take(6) + quizHard.shuffled().take(4)).toMutableList()
            }

            4 -> {
                allQuestions = (quizEasy + quizMedium + quizHard).toMutableList()
            }
        }
        return allQuestions.size
    }

    fun getSize() {
        //Log (quizEasy + quizMedium + quizHard)
        Log.d("Quiz easy", "Easy Size: ${quizEasy.size}")
        Log.d("Quiz medium", "Medium Size: ${quizMedium.size}")
        Log.d("Quiz hard", "Hard Size: ${quizHard.size}")
    }

    private val quizEasy = listOf(
        QuizModel(
            "Easy quiz question 1?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 2?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 3?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 4?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 5?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 6?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 7?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 8?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 9?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Easy quiz question 10?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
    )
    private val quizMedium = listOf(
        QuizModel(
            "Medium quiz question 1?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 2?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 3?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 4?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 5?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 6?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 7?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 8?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 9?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Medium quiz question 10?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
    )
    private val quizHard = listOf(
        QuizModel(
            "Hard quiz question 1?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 2?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 3?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 4?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 5?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 6?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 7?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 8?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 9?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
        QuizModel(
            "Hard quiz question 10?",
            "Correct answer",
            "Wrong answer",
            "Wrong answer",
            "Wrong answer"
        ),
    )
}