package com.oyoke.iara.games.would_he_rather

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
    fun getSize(): Int {
        return WouldHeRatherRepository.getSize()
    }
}