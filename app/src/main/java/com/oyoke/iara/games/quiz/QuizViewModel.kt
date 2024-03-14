package com.oyoke.iara.games.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    val quizModel = MutableLiveData<QuizModel>()

    fun randomQuiz() {
        val currentQuote = QuizRepository.random()
        quizModel.value = currentQuote

    }

    fun getQuestions(difficulty: Int): Int {
        return QuizRepository.getQuestions(difficulty)
    }
    fun getSize(){
        QuizRepository.getSize()
    }
}