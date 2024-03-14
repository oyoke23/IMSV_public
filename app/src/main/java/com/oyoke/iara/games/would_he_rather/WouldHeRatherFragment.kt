package com.oyoke.iara.games.would_he_rather

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.oyoke.iara.MainActivity
import com.oyoke.iara.R
import com.oyoke.iara.databinding.FragmentWouldHeRatherBinding
import com.oyoke.iara.util.music.PlayMusic

class WouldHeRatherFragment : Fragment() {
    private var _binding: FragmentWouldHeRatherBinding? = null
    private val binding get() = _binding!!

    private val wouldHeRatherModel: WouldHeRatherViewModel by viewModels()

    private lateinit var correctSoundPlayer: MediaPlayer
    private lateinit var incorrectSoundPlayer: MediaPlayer

    private lateinit var correct: String
    private var answerTime: Long = 0
    private var category = ""
    private var currentDifficulty = 1

    var defaultColor1 = 0
    var defaultColor2 = 0

    private var allQuestionsSize: Int = 0
    private var usedQuestions = 0

    private val wouldHeRatherObserver = Observer<WouldHeRatherModel> { currentItem ->
        correct = currentItem.answer

        val options = listOf(
            currentItem.answer, currentItem.wrongAnswer
        )
        val shuffledOptions = options.shuffled()

        binding.tvOption1.text = shuffledOptions[0]
        binding.tvOption2.text = shuffledOptions[1]
    }
    private var wouldHeRatherModelLiveData: LiveData<WouldHeRatherModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWouldHeRatherBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("WHR")

        if (PlayMusic.getMediaPlayer() == null) {
            PlayMusic.playRandomSongs(requireContext(), "QuizWhr")
        }

        category = arguments?.getString("category").toString()
        currentDifficulty = arguments?.getInt("difficulty") ?: 1
        allQuestionsSize = wouldHeRatherModel.getQuestions(category)
        wouldHeRatherModel.getSize()
        correctSoundPlayer = MediaPlayer.create(requireContext(), R.raw.correct_sound)
        incorrectSoundPlayer = MediaPlayer.create(requireContext(), R.raw.incorrect_sound)

        defaultColor1 = binding.cvOption1.cardBackgroundColor.defaultColor
        defaultColor2 = binding.cvOption2.cardBackgroundColor.defaultColor

        answerTime = arguments?.getLong("answerTime")!!

        setTimer()

        return binding.root
    }

    private fun setTimer() {
        (activity as? MainActivity)?.removeTimer("countDown")
        (activity as? MainActivity)?.updateTimer(answerTime, "countDown")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wouldHeRatherModelLiveData = wouldHeRatherModel.wouldHeRatherModel
        wouldHeRatherModelLiveData?.observe(viewLifecycleOwner, wouldHeRatherObserver)

        assignAndShuffle()


        binding.cvOption1.setOnClickListener {

            checkAnswer(binding.tvOption1.text.toString(), binding.cvOption1, binding.cvOption2)

        }
        binding.cvOption2.setOnClickListener {
            checkAnswer(binding.tvOption2.text.toString(), binding.cvOption2, binding.cvOption1)

        }
    }
    private fun assignAndShuffle() {
        when (usedQuestions) {
            allQuestionsSize -> {
                val arguments = Bundle().apply {
                    putString("type", "Win - WHR")
                }

                findNavController().navigate(R.id.finalScreenFragment, arguments)
            }

            else -> {
                wouldHeRatherModel.randomWouldHeRather()
            }
        }

    }
    private fun checkAnswer(
        text: String, cardView1: MaterialCardView, cardView2: MaterialCardView
    ) {
        cardView1.isEnabled = false
        cardView2.isEnabled = false

        if (text == correct) {
            playCorrectSound()
            cardView1.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(), R.color.correctAnswer
                )
            )
            setTimer()
        } else {
            playIncorrectSound()
            cardView1.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(), R.color.wrongAnswer
                )
            )
            adjustTimer(currentDifficulty)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            usedQuestions++
            assignAndShuffle()

            binding.cvOption1.setCardBackgroundColor(defaultColor1)
            binding.cvOption2.setCardBackgroundColor(defaultColor2)

            binding.cvOption1.isEnabled = true
            binding.cvOption2.isEnabled = true

        }, 500)

    }

    private fun adjustTimer(difficulty: Int) {
        when (difficulty) {
            2 -> {
                (activity as? MainActivity)?.decreaseTimer(20000)
            }

            else -> {
                setTimer()
            }
        }
    }

    private fun playCorrectSound() {
        correctSoundPlayer.seekTo(0)
        correctSoundPlayer.start()
    }

    private fun playIncorrectSound() {
        incorrectSoundPlayer.seekTo(0)
        incorrectSoundPlayer.start()
    }

    override fun onResume() {
        super.onResume()
        PlayMusic.resume()
    }

    override fun onPause() {
        super.onPause()
        PlayMusic.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        (activity as? MainActivity)?.removeTimer("countDown")
        wouldHeRatherModelLiveData?.removeObserver(wouldHeRatherObserver)
        wouldHeRatherModelLiveData = null
    }
}