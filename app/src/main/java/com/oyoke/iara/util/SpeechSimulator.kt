package com.oyoke.iara.util

import android.os.Handler
import android.widget.TextView

class SpeechSimulator(
    private val textView: TextView,
    private val listener: OnSimulationCompleteListener
) {
    private var index = 0
    private val handler = Handler()
    private var speechText: String? = null

    private val simulateRunnable = object : Runnable {
        override fun run() {
            if (speechText != null) {
                textView.text = speechText!!.substring(0, index++)
                if (index <= speechText!!.length) {
                    handler.postDelayed(this, 30) // Ajusta la velocidad de escritura
                } else {
                    // La simulación ha terminado
                    listener.onSimulationComplete()
                }
            }
        }
    }

    fun startSimulation(newSpeechText: String) {
        reset()
        speechText = newSpeechText
        handler.postDelayed(simulateRunnable, 0)
    }

    fun stopSimulation() {
        handler.removeCallbacks(simulateRunnable)
        reset()
    }

    private fun reset() {
        index = 0
        textView.text = ""
        speechText = null
    }

    interface OnSimulationCompleteListener {
        fun onSimulationComplete()
    }
}
