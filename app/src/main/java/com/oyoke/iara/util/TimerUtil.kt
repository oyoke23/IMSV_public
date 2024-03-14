package com.oyoke.iara.util

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.navigation.NavController
import com.oyoke.iara.R
import java.util.concurrent.TimeUnit


class TimerUtil private constructor(
    totalTime: Long?,
    private val textView: TextView,
    private val navController: NavController,
    private val typeTimer: String
) {
    private var countDownTimer: CountDownTimer? = null
    private var timeRemaining: Long? = totalTime

    private var countTimer: Handler? = null
    private var countRunnable: Runnable? = null
    private var timeElapsed: Long = 0

    init {
        when (typeTimer) {
            "count" -> {
                createCountTimer()
            }

            "countDown" -> {
                createCountDownTimer()
            }
        }
    }

    private fun createCountTimer() {
        countTimer = Handler(Looper.getMainLooper())
        countRunnable = object : Runnable {
            override fun run() {
                timeElapsed += 1000
                val minutes = TimeUnit.MILLISECONDS.toMinutes(timeElapsed)
                val seconds =
                    TimeUnit.MILLISECONDS.toSeconds(timeElapsed) - TimeUnit.MINUTES.toSeconds(
                        minutes
                    )
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                textView.text = formattedTime
                countTimer?.postDelayed(this, 1000)
            }
        }
    }

    private fun createCountDownTimer() {
        if (timeRemaining != null) {
            countDownTimer = object : CountDownTimer(timeRemaining!!, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemaining = millisUntilFinished
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeRemaining!!)
                    val seconds =
                        TimeUnit.MILLISECONDS.toSeconds(timeRemaining!!) - TimeUnit.MINUTES.toSeconds(
                            minutes
                        )
                    val formattedTime = String.format("%02d:%02d", minutes, seconds)
                    textView.text = formattedTime
                }

                override fun onFinish() {
                    textView.text = ""
                    countDownTimer = null
                    val arguments = Bundle().apply {
                        putString("type", "Lose")
                    }

                    navController.navigate(R.id.finalScreenFragment, arguments)

                }
            }
        } else {
            Log.d("ERROR:", "createCountDownTimer get a NULL timeRemaining value")
        }

    }
    fun startTimer() {
        when (typeTimer) {
            "count" -> {
                countTimer?.post(countRunnable!!)
            }

            "countDown" -> {
                countDownTimer?.start()
            }
        }
    }

    fun removeTimer() {
        when (typeTimer) {
            "count" -> {
                countTimer?.removeCallbacks(countRunnable!!)
                countTimer = null
                countRunnable = null
                timeElapsed = 0
                textView.text = ""
            }

            "countDown" -> {
                countDownTimer?.cancel()
                countDownTimer = null
                textView.text = ""
            }
        }
    }

    fun decreaseTimer(timeToDecrease: Long) {
        timeRemaining = timeRemaining?.minus(timeToDecrease)
        countDownTimer?.cancel()
        createCountDownTimer()
        countDownTimer?.start()
    }

    companion object {
        private var instance: TimerUtil? = null

        fun getInstance(
            totalTime: Long?,
            textView: TextView,
            navController: NavController,
            typeTimer: String
        ): TimerUtil {
            if (instance == null) {
                instance = TimerUtil(totalTime, textView, navController, typeTimer)
            }
            return instance!!
        }

        fun reset() {
            instance = null
        }
    }
}









