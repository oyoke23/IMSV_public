package com.oyoke.iara.games.quiz


import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
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
import com.oyoke.iara.databinding.FragmentQuizBinding
import com.oyoke.iara.util.music.PlayMusic

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var correctSoundPlayer: MediaPlayer
    private lateinit var incorrectSoundPlayer: MediaPlayer

    private var allQuestionsSize: Int = 0
    private var usedQuestions = 0

    private var originalButtonColor: ColorDrawable? = null

    private var difficulty = 0

    private val quizObserver = Observer<QuizModel> { currentQuiz ->
        binding.tvQuiz.text = currentQuiz.question
        val options = listOf(
            currentQuiz.answer,
            currentQuiz.incorrect1,
            currentQuiz.incorrect2,
            currentQuiz.incorrect3
        )
        val shuffledOptions = options.shuffled()

        binding.tvOption1.text = shuffledOptions[0]
        binding.tvOption2.text = shuffledOptions[1]
        binding.tvOption3.text = shuffledOptions[2]
        binding.tvOption4.text = shuffledOptions[3]
    }
    private var quizModelLiveData: LiveData<QuizModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("Quiz")
        difficulty = arguments?.getInt("difficulty")!!

        if (PlayMusic.getMediaPlayer() == null) {
            PlayMusic.playRandomSongs(requireContext(), "QuizWhr")
        }

        allQuestionsSize = quizViewModel.getQuestions(difficulty)
        quizViewModel.getSize()
        correctSoundPlayer = MediaPlayer.create(requireContext(), R.raw.correct_sound)
        incorrectSoundPlayer = MediaPlayer.create(requireContext(), R.raw.incorrect_sound)

        originalButtonColor = ColorDrawable(binding.btn1.backgroundTintList?.defaultColor ?: 0)

        setTimer(difficulty)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizModelLiveData = quizViewModel.quizModel
        quizModelLiveData?.observe(viewLifecycleOwner, quizObserver)

        assignAndShuffle()

        binding.btn1.setOnClickListener {
            checkAnswer(binding.btn1, binding.tvOption1.text.toString())
        }

        binding.btn2.setOnClickListener {
            checkAnswer(binding.btn2, binding.tvOption2.text.toString())
        }

        binding.btn3.setOnClickListener {
            checkAnswer(binding.btn3, binding.tvOption3.text.toString())
        }

        binding.btn4.setOnClickListener {
            checkAnswer(binding.btn4, binding.tvOption4.text.toString())
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
        binding.progressBar.progress = 0
    }

    override fun onPause() {
        super.onPause()
        PlayMusic.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        (activity as? MainActivity)?.removeTimer("countDown")
        correctSoundPlayer.release()
        incorrectSoundPlayer.release()
        quizModelLiveData?.removeObserver(quizObserver)
        quizModelLiveData = null
    }

    private fun assignAndShuffle() {
        when (usedQuestions) {
            allQuestionsSize -> {
                val arguments = Bundle().apply {
                    putString("type", "Win - Quiz")
                }

                findNavController().navigate(R.id.finalScreenFragment, arguments)
            }

            else -> {
                quizViewModel.randomQuiz()
            }
        }

    }

    private fun changeProgressBarColor() {
        val binding = retrieveBinding() ?: return

        if (isAdded && !isDetached && !isRemoving) {
            usedQuestions++

            val progress = (usedQuestions.toFloat() / allQuestionsSize.toFloat() * 100).toInt()

            binding.progressBar.progress = progress
        }
    }

    private fun checkAnswer(button: MaterialCardView, selectedAnswer: String) {
        val currentQuiz = quizViewModel.quizModel.value
        if (currentQuiz != null) {

            val correctAnswer = currentQuiz.answer

            if (selectedAnswer == correctAnswer) {
                playCorrectSound()
                requireContext().let {
                    ContextCompat.getColor(it, R.color.correctAnswer)
                }.let { button.backgroundTintList = ColorStateList.valueOf(it) }
                Handler(Looper.getMainLooper()).postDelayed({
                    changeProgressBarColor()
                    assignAndShuffle()
                    resetButtons()
                    setTimer(difficulty)
                }, 1000)
            } else {
                playIncorrectSound()
                decreaseTimer(difficulty)
                requireContext().let {
                    ContextCompat.getColor(it, R.color.wrongAnswer)
                }.let { button.backgroundTintList = ColorStateList.valueOf(it) }
                button.isEnabled = false
            }

        }
    }

    private fun resetButtons() {
        val actualBinding = retrieveBinding()

        if (isAdded && actualBinding != null) {
            actualBinding.btn1.isEnabled = true
            actualBinding.btn2.isEnabled = true
            actualBinding.btn3.isEnabled = true
            actualBinding.btn4.isEnabled = true

            actualBinding.btn1.backgroundTintList =
                originalButtonColor?.color?.let { ColorStateList.valueOf(it) }
            actualBinding.btn2.backgroundTintList =
                originalButtonColor?.color?.let { ColorStateList.valueOf(it) }
            actualBinding.btn3.backgroundTintList =
                originalButtonColor?.color?.let { ColorStateList.valueOf(it) }
            actualBinding.btn4.backgroundTintList =
                originalButtonColor?.color?.let { ColorStateList.valueOf(it) }
        }
    }

    private fun setTimer(difficulty: Int) {
        (activity as? MainActivity)?.removeTimer("countDown")
        when (difficulty) {
            1 -> {
                (activity as? MainActivity)?.updateTimer(30000, "countDown")
            }

            2 -> {
                (activity as? MainActivity)?.updateTimer(25000, "countDown")
            }

            3 -> {
                (activity as? MainActivity)?.updateTimer(20000, "countDown")
            }

            4 -> {
                (activity as? MainActivity)?.updateTimer(15000, "countDown")
            }
        }
    }

    private fun decreaseTimer(difficulty: Int) {
        when (difficulty) {
            1 -> {
                (activity as? MainActivity)?.decreaseTimer(10000)
            }

            2 -> {
                (activity as? MainActivity)?.decreaseTimer(10000)
            }

            3 -> {
                (activity as? MainActivity)?.decreaseTimer(12000)
            }

            4 -> {
                (activity as? MainActivity)?.decreaseTimer(15000)
            }
        }
    }

    private fun retrieveBinding(): FragmentQuizBinding? {
        return if (isAdded && view != null) {
            FragmentQuizBinding.bind(requireView())
        } else {
            null
        }
    }
}