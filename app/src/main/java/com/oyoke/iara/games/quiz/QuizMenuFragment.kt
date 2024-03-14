package com.oyoke.iara.games.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyoke.iara.MainActivity
import com.oyoke.iara.R
import com.oyoke.iara.databinding.FragmentQuizMenuBinding
import com.oyoke.iara.util.music.PlayMusic

class QuizMenuFragment : Fragment() {
    private var _binding: FragmentQuizMenuBinding? = null
    private val binding get() = _binding!!

    private var currentDifficulty = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizMenuBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.updateFragment("Quiz Menu")

        if (PlayMusic.getMediaPlayer() == null) {
            PlayMusic.playRandomSongs(requireContext(), "QuizWhr")
        }
        updateDifficultyText()
        binding.btnLeftArrow.setOnClickListener {
            decreaseDifficulty()
        }

        binding.btnRightArrow.setOnClickListener {
            increaseDifficulty()
        }
        binding.btnDifficulty.setOnClickListener {
            PlayMusic.pause()
            startGame()

        }
        return binding.root
    }

    private fun decreaseDifficulty() {
        if (currentDifficulty > 1) {
            currentDifficulty--
            updateDifficultyText()
        }
    }

    private fun increaseDifficulty() {
        // Puedes ajustar el límite superior según tu necesidad
        if (currentDifficulty < 4) {
            currentDifficulty++
            updateDifficultyText()
        }
    }

    private fun updateDifficultyText() {

        when (currentDifficulty) {
            1 -> {
                binding.tvDifficulty.text = getString(R.string.fragment_quiz_menu_difficulty_easy)
                binding.cvTvTitle.text = getString(R.string.fragment_quiz_menu_title_easy)
                binding.cvIvDifficulty.setImageResource(R.drawable.quiz_easy_difficulty)
                binding.cvTvExplanation.text =
                    getString(R.string.fragment_quiz_menu_explanation_easy)
                binding.btnLeftArrow.isClickable = false
                binding.btnLeftArrow.visibility = View.INVISIBLE
            }

            2 -> {
                binding.tvDifficulty.text = getString(R.string.fragment_quiz_menu_difficulty_normal)
                binding.cvTvTitle.text = getString(R.string.fragment_quiz_menu_title_normal)
                binding.cvIvDifficulty.setImageResource(R.drawable.quiz_normal_difficulty)
                binding.cvTvExplanation.text =
                    getString(R.string.fragment_quiz_menu_explanation_normal)
                binding.btnLeftArrow.isClickable = true
                binding.btnLeftArrow.visibility = View.VISIBLE
            }

            3 -> {
                binding.tvDifficulty.text = getString(R.string.fragment_quiz_menu_difficulty_hard)
                binding.cvTvTitle.text = getString(R.string.fragment_quiz_menu_title_hard)
                binding.cvIvDifficulty.setImageResource(R.drawable.quiz_hard_difficulty)
                binding.cvTvExplanation.text =
                    getString(R.string.fragment_quiz_menu_explanation_hard)
                binding.btnRightArrow.isClickable = true
                binding.btnRightArrow.visibility = View.VISIBLE
            }

            4 -> {
                binding.tvDifficulty.text = getString(R.string.fragment_quiz_menu_difficulty_expert)
                binding.cvTvTitle.text = getString(R.string.fragment_quiz_menu_title_expert)
                binding.cvIvDifficulty.setImageResource(R.drawable.quiz_expert_difficulty)
                binding.cvTvExplanation.text =
                    getString(R.string.fragment_quiz_menu_explanation_expert)

                binding.btnRightArrow.isClickable = false
                binding.btnRightArrow.visibility = View.INVISIBLE
            }
        }

    }

    private fun startGame() {
        val quizFragment = QuizFragment().apply {
            arguments = Bundle().apply {
                putInt("difficulty", currentDifficulty)
            }
        }
        findNavController().navigate(R.id.quizFragment, quizFragment.arguments)
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
    }
}