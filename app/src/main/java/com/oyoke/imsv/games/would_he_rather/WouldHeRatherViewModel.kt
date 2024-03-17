package com.oyoke.imsv.games.would_he_rather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WouldHeRatherViewModel: ViewModel() {
    val wouldHeRatherModel = MutableLiveData<WouldHeRatherModel>()

    fun randomWouldHeRather() {
        val currentList = WouldHeRatherRepository.random()
        wouldHeRatherModel.value = currentList
    }

    fun getQuestions(category: String): Int{
        return WouldHeRatherRepository.generateQuestions(category)
    }
}